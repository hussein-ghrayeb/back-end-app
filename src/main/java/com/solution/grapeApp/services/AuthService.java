package com.solution.grapeApp.services;



import com.solution.grapeApp.config.JwtService;
import com.solution.grapeApp.entities.requests.AuthenticationRequest;
import com.solution.grapeApp.entities.responses.AuthenticationResponse;
import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Optional<Customer> user = repository.findUserByUsername(request.getUsername());
        if(user.isEmpty()){
            return AuthenticationResponse.builder().message("Wrong username or password").build();
        }
        else {
            if (request.getPassword().equals(user.get().getPassword()))  {
                var jwtToken = jwtService.generateToken(user.get());
                return AuthenticationResponse.builder().token(jwtToken).message("User Logged In Successfully").build();
            }
            else {
                return AuthenticationResponse.builder().message("Wrong username or password").build();
            }

        }
    }

}
