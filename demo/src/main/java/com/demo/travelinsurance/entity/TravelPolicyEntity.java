package com.demo.travelinsurance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "travel_policy")
@Data
public class TravelPolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_number", nullable = false, unique = true, length = 50)
    private String policyNumber;

    @Column(name = "destination_country", nullable = false, length = 50)
    private String destinationCountry;

    @Column(name = "travel_days", nullable = false)
    private Integer travelDays;

    @Column(name = "premium_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal premiumAmount;

    @Column(name = "policy_start_date", nullable = false)
    private LocalDate policyStartDate;
}