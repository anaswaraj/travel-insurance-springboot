package com.demo.travelinsurance.commonmapping;

import com.demo.travelinsurance.dto.HttpBinRequest;
import com.demo.travelinsurance.dto.PolicyRequest;
import org.springframework.stereotype.Service;

@Service
public class ComonMapping {

    public HttpBinRequest newRquestForBin(PolicyRequest policyRequest) {
        HttpBinRequest request = new HttpBinRequest();
        request.setPolicyType("TRAVEL");
        request.setCustomerName("Rahul Kumar");
        request.setAmount("2500");
        return request;
    }
}
