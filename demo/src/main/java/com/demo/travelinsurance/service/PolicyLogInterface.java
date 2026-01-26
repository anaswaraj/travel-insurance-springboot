package com.demo.travelinsurance.service;

import com.demo.travelinsurance.dto.PolicyRequest;
import com.demo.travelinsurance.entity.PolicyIssuanceLog;
import com.demo.travelinsurance.exception.PolicyCreationException;
import org.springframework.stereotype.Service;

@Service
public interface PolicyLogInterface {
    void savePolicyToLog(PolicyIssuanceLog policyIssuanceLog,PolicyRequest policyRequest) throws PolicyCreationException;
}
