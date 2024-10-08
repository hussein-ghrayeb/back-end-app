package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.requests.AuthenticationRequest;
import com.solution.grapeApp.entities.responses.AuthenticationResponse;
import com.solution.grapeApp.enums.AuthType;
import com.solution.grapeApp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        if (request.getAuthType().name() == AuthType.CUSTOMER.name())
            return ResponseEntity.ok(authService.authenticateCustomer(request));
        else if (request.getAuthType().name() == AuthType.EMPLOYEE.name())
            return ResponseEntity.ok(authService.authenticateEmployee(request));
        return ResponseEntity.ok(authService.authenticateUser(request));
    }

}
