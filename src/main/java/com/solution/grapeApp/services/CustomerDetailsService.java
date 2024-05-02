package com.solution.grapeApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.repositories.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optional = customerRepository.findCustomerByUsername(username);
        return optional.get();
    }

}
