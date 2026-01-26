package com.demo.travelinsurance.controller;

import com.demo.travelinsurance.constant.ErrorCode;
import com.demo.travelinsurance.constant.TravelApiConstant;
import com.demo.travelinsurance.dto.PolicyRequest;
import com.demo.travelinsurance.dto.PolicyResponse;
import com.demo.travelinsurance.entity.PolicyIssuanceLog;
import com.demo.travelinsurance.entity.TravelPolicyEntity;
import com.demo.travelinsurance.error.Status;
import com.demo.travelinsurance.exception.PolicyCreationException;
import com.demo.travelinsurance.repository.PolicyIssuanceLogRepository;
import com.demo.travelinsurance.service.PolicyLogInterface;
import com.demo.travelinsurance.service.PolicyService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/travel/policy")
public class DigitPolicyTravelController {

    private static final Logger logger =
            LoggerFactory.getLogger(DigitPolicyTravelController.class);

    @Autowired
    PolicyService policyService;

    @Autowired
    PolicyIssuanceLogRepository policyIssuanceLogRepository;

    @Autowired
    PolicyLogInterface policyLogInterface;

    @Autowired
    Gson gson;

    @PostMapping("/insert")
    public ResponseEntity<?> insertPolicy(@RequestBody PolicyRequest request, HttpServletRequest httpReq) {
        String path = httpReq.getRequestURI();
        logger.info("START insertPolicy path={} request={}", path, request);

        try {
            // basic validation (optional but useful)
            if (request == null) {
                throw new IllegalArgumentException("No request body provided");
            }

            policyService.createPolicy(request);
            Status status = new Status();
            status.setStatusCode(200);
            status.setStatusMessage("Success");

            logger.info("SUCCESS");
            // Use CREATED if you are creating a new policy
            return new ResponseEntity<>(status, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            logger.error("BAD_REQUEST buyPolicy path={} msg={}", path, e.getMessage());
            Status status = new Status();
            status.setStatusCode(TravelApiConstant.StatusCode.BAD_REQUEST);
            status.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            logger.error("ERROR buyPolicy path={} ", path, e);
            Status status = new Status();
            status.setStatusCode(TravelApiConstant.StatusCode.INTERNAL_ERROR);
            status.setStatusMessage("Something went wrong while buying policy");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createPolicy")
    public ResponseEntity<?> createPolicy(@RequestBody PolicyRequest policyRequest, HttpServletRequest httpReq) {
        logger.info("start of createPolicy");
        logger.info("RequestBody:: " + policyRequest);
        PolicyIssuanceLog policyIssuanceLog = new PolicyIssuanceLog();
        PolicyResponse policyResponse = new PolicyResponse();
        Status status = new Status();
        try {
            policyIssuanceLog.setBookingId(policyRequest.getBookingId());
            policyIssuanceLog.setRequestedTimestamp(LocalDateTime.now());
            policyIssuanceLog.setPolicyRequest(gson.toJson(policyRequest));
            policyLogInterface.savePolicyToLog(policyIssuanceLog, policyRequest);
            status.setStatusCode(200);
            status.setStatusMessage("Success");
            policyResponse.setStatus(status);
            policyResponse.setBookingId(policyRequest.getBookingId());
            policyResponse.setDate(LocalDateTime.now());
            policyResponse.setPolicyNumber("D"+System.currentTimeMillis());
            String response=gson.toJson(policyResponse);
            policyIssuanceLog.setPolicyResponse(response);
            policyIssuanceLogRepository.save(policyIssuanceLog);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (PolicyCreationException pe) {
            logger.error("Inside PolicyCreationException:: " + pe.getMessage());
            policyIssuanceLog.setErrorLog(pe.getMessage());
            policyIssuanceLog.setInternalResponse(gson.toJson(pe.getMessage()));
            policyIssuanceLog.setResponseTimestamp(LocalDateTime.now());
            policyIssuanceLogRepository.save(policyIssuanceLog);
            status.setStatusCode(TravelApiConstant.StatusCode.BAD_REQUEST);
            status.setStatusMessage(pe.getMessage());
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException iae) {
            logger.error("Inside IllegalArgumentException:: " + iae.getMessage());
            policyIssuanceLog.setErrorLog(iae.getMessage());
            policyIssuanceLog.setInternalResponse(gson.toJson(iae.getMessage()));
            policyIssuanceLog.setResponseTimestamp(LocalDateTime.now());
            policyIssuanceLogRepository.save(policyIssuanceLog);
            status.setStatusCode(TravelApiConstant.StatusCode.BAD_REQUEST);
            status.setStatusMessage(iae.getMessage());
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Inside Exception:: " + e.getMessage());
            policyIssuanceLog.setErrorLog(e.getMessage());
            policyIssuanceLog.setInternalResponse(gson.toJson(e.getMessage()));
            policyIssuanceLog.setResponseTimestamp(LocalDateTime.now());
            policyIssuanceLogRepository.save(policyIssuanceLog);
            status.setStatusCode(TravelApiConstant.StatusCode.INTERNAL_ERROR);
            status.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
