package com.demo.travelinsurance.dto;

import lombok.Data;

@Data
public class Address {

    private String line1;
    private String state;
    private String city;
    private String country;
    private String pinCode;
}
