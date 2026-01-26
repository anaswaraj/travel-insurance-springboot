package com.demo.travelinsurance.dto;

import lombok.Data;

@Data
public class Flight {

    private String journeyType;
    private String leg;

    private String source;
    private String destination;

    private String startDate;
    private String endDate;

    private String providerName;
    private String providerTravelNo;

    private String classType;
    private String ticketNumber;

    private String travelMode;

    private String baseFare;
    private String feesSurcharge;
    private String convenienceFee;
    private String cancellationCharge;
}
