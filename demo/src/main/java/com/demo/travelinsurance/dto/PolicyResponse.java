package com.demo.travelinsurance.dto;

import com.demo.travelinsurance.error.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PolicyResponse {
    Status status;
    private String policyNumber;
    private BigDecimal premiumAmount;
    private String message;
}
