package com.demo.travelinsurance.service.impl;

import com.demo.travelinsurance.client.HttpBinService;
import com.demo.travelinsurance.commonmapping.ComonMapping;
import com.demo.travelinsurance.dto.HttpBinRequest;
import com.demo.travelinsurance.dto.HttpBinResponse;
import com.demo.travelinsurance.dto.PolicyRequest;
import com.demo.travelinsurance.entity.PolicyIssuanceLog;
import com.demo.travelinsurance.exception.PolicyCreationException;
import com.demo.travelinsurance.service.PolicyLogInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// Add Gson import
import com.google.gson.Gson;

@Service
public class PolicyLogInterfaceImpl implements PolicyLogInterface {

    private static final Logger logger =
            LoggerFactory.getLogger(PolicyLogInterfaceImpl.class);

    @Autowired
    HttpBinService httpBinService;

    @Autowired
    ComonMapping comonMapping;

    @Autowired
    Gson gson;

    @Override
    public void savePolicyToLog(PolicyIssuanceLog policyIssuanceLog, PolicyRequest policyRequest) throws PolicyCreationException {
        // Use injected Gson to serialize request/response objects to JSON for logging/storage
        try {
            logger.info("Start savePolicyToLog");
            HttpBinRequest httpBinRequest = comonMapping.newRquestForBin(policyRequest);
            // Serialize the request object to JSON
            policyIssuanceLog.setInternalRequest(gson.toJson(httpBinRequest));
            HttpBinResponse responseJson = httpBinService.callPostBinApi(httpBinRequest);
            // Serialize the response object to JSON
            policyIssuanceLog.setInternalResponse(gson.toJson(responseJson));
            policyIssuanceLog.setResponseTimestamp(LocalDateTime.now());
        } catch (PolicyCreationException pe) {
            logger.error("Inside PolicyCreationException:: " + pe.getMessage());
            throw new PolicyCreationException(pe.getMessage(), pe);
        } catch (IllegalArgumentException iae) {
            logger.error("Inside IllegalArgumentException:: " + iae.getMessage());
            throw new PolicyCreationException(iae.getMessage(), iae);
        } catch (Exception e) {
            logger.error("Inside Exception:: " + e.getMessage());
            throw new PolicyCreationException(e.getMessage(), e);
        }
    }
}
