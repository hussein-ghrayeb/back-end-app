package com.solution.grapeApp.entities.responses;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Customer customer;
    private Employee employee;
    private String message;
}
