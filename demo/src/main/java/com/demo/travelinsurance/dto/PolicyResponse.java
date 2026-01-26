package com.demo.travelinsurance.dto;

import com.demo.travelinsurance.error.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PolicyResponse {
    Status status;
    private String bookingId;
    private String policyNumber;
    private BigDecimal premiumAmount;
    private String message;
    LocalDateTime date;
}
