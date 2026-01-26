package com.demo.travelinsurance.dto;

import com.demo.travelinsurance.error.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PolicyResponse {
    Status status;
    private String bookingId;
    private String policyNumber;
    private BigDecimal premiumAmount;
    private String message;
    LocalDateTime date;
}
