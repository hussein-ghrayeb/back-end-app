package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.entities.CustomerDTO;
import com.solution.grapeApp.entities.Order;
import com.solution.grapeApp.entities.Setting;
import com.solution.grapeApp.services.CustomerService;
import java.util.Optional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.solution.grapeApp.repositories.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

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
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/forgetPassword")
    public ResponseEntity<Customer> updateSetting(@RequestBody CustomerDTO customerDTO) {
        try {
            Optional<Customer> optional = customerRepository.findCustomerByUsername(customerDTO.getUsername());
            if (optional.isPresent()) {
                optional.get().setPassword(customerDTO.getPassword());
                return ResponseEntity.ok(customerService.saveCustomer(optional.get()));
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getCustomerByPhoneNumber")
    public ResponseEntity<Customer> getCustomerByPhoneNumber(@RequestParam String number) {
        Optional<Customer> optional = customerRepository.findCustomerByUsername(number);

        if (optional.isPresent()) {
            Customer customer = optional.get();
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.noContent().build();
        }
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
