package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(
            @RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.saveCustomer(customer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
