package com.demo.travelinsurance.controller;

import com.demo.travelinsurance.entity.CustomerEntity;
import com.demo.travelinsurance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    @Autowired
    CustomerRepository repository;

    @PostMapping
    public CustomerEntity create(@RequestBody CustomerEntity customerEntity) {
        return repository.save(customerEntity);
    }

    @GetMapping("/{id}")
    public CustomerEntity getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
