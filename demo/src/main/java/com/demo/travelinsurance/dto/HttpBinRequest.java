package com.demo.travelinsurance.dto;

import lombok.Data;

@Data
public class HttpBinRequest {
    String policyType;
    String customerName;
    String amount;
}
