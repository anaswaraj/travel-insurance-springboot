package com.demo.travelinsurance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "policyissuancelog")
@Data
public class PolicyIssuanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id", nullable = false, length = 100)
    private String bookingId;

    @Column(name = "error_log", columnDefinition = "TEXT")
    private String errorLog;

    @Column(name = "requested_timestamp")
    private LocalDateTime requestedTimestamp;

    @Column(name = "response_timestamp")
    private LocalDateTime responseTimestamp;

    @Column(name = "policy_request", columnDefinition = "TEXT")
    private String policyRequest;

    @Column(name = "policy_response", columnDefinition = "TEXT")
    private String policyResponse;

    @Column(name = "internal_request", columnDefinition = "TEXT")
    private String internalRequest;

    @Column(name = "internal_response", columnDefinition = "TEXT")
    private String internalResponse;


}
