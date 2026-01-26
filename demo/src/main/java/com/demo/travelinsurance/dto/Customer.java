package com.demo.travelinsurance.dto;

import lombok.Data;
import java.util.List;

@Data
public class Customer {

    private String title;

    private Address address;

    private List<Document> documents;

    private String mobile;
    private String type;
    private String gender;

    private String companyName;
}
