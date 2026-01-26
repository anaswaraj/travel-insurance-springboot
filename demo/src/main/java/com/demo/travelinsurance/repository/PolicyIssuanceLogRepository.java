package com.demo.travelinsurance.repository;

import com.demo.travelinsurance.entity.PolicyIssuanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyIssuanceLogRepository extends JpaRepository<PolicyIssuanceLog,Long> {
}
