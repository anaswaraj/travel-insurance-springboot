package com.demo.travelinsurance.service;

import com.demo.travelinsurance.dto.PolicyRequest;
import com.demo.travelinsurance.entity.TravelPolicyEntity;
import org.springframework.stereotype.Service;

@Service
public interface PolicyService {
    void createPolicy(PolicyRequest request);
    TravelPolicyEntity getPolicy(String policyNumber);
}
