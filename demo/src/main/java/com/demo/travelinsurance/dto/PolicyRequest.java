package com.demo.travelinsurance.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PolicyRequest {
    String DestinationCountry;
    int TravelDays;
    BigDecimal PremiumAmount;
    String PolicyStartDate;

    private String bookingId;
    private String provider;
    private String type;

    private Customer customer;
    private List<InsuredPerson> insuredPersons;

    private String insuredPersonsCount;
    private String bookingDate;

    private String packageName;
    private String geography;

    private String travelEndDate;
    private String travelStartDate;

    private String countryTravel;
    private String partner;

    private Boolean isQuoteCall;
}
