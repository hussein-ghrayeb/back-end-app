package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.entities.Order;
import com.solution.grapeApp.services.CustomerService;

import java.util.List;

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

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<Void> deleteCustomer(@RequestParam String id) {
        try {
            if (customerService.isCustomerExists(id)) {
                customerService.deleteCustomerById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
