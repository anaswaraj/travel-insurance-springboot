package com.demo.travelinsurance.repository;

import com.demo.travelinsurance.entity.TravelPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PolicyRepository extends JpaRepository<TravelPolicyEntity, Long> {
    Optional<TravelPolicyEntity> findByPolicyNumber(String policyNumber);
}
