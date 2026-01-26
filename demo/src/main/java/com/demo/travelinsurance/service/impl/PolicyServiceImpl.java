package com.demo.travelinsurance.service.impl;

import com.demo.travelinsurance.dto.PolicyRequest;
import com.demo.travelinsurance.entity.TravelPolicyEntity;
import com.demo.travelinsurance.exception.BusinessException;
import com.demo.travelinsurance.exception.ResourceNotFoundException;
import com.demo.travelinsurance.repository.PolicyRepository;
import com.demo.travelinsurance.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    PolicyRepository policyRepository;


    @Override
    public void createPolicy(PolicyRequest request) {

        if (request.getTravelDays() <= 0) {
            throw new BusinessException("Travel days must be greater than zero");
        }

        if (request.getDestinationCountry() == null || request.getDestinationCountry().isBlank()) {
            throw new BusinessException("Destination country is required");
        }

        // Simple premium logic for teaching
        BigDecimal premium = BigDecimal.valueOf(request.getTravelDays() * 50.0);

        TravelPolicyEntity policy = new TravelPolicyEntity();
        policy.setPolicyNumber("D" + System.currentTimeMillis());
        policy.setDestinationCountry(request.getDestinationCountry());
        policy.setTravelDays(request.getTravelDays());
        policy.setPremiumAmount(premium);
        policy.setPolicyStartDate(LocalDate.now());

        policyRepository.save(policy);
    }

    @Override
    public TravelPolicyEntity getPolicy(String policyNumber) {
        return policyRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
    }
}
