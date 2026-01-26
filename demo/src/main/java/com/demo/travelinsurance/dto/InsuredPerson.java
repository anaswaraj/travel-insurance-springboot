package com.demo.travelinsurance.dto;

import lombok.Data;
import java.util.List;

@Data
public class InsuredPerson {

    private String title;
    private String firstName;
    private String lastName;

    private Address address; // in your JSON it's {} sometimes, still fine

    private String dob;
    private String mobile;
    private String email;

    private String paxId;
    private String type;
    private String gender;

    private List<Flight> flights;
}
