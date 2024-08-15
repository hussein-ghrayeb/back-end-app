package com.solution.grapeApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solution.grapeApp.entities.Employee;
import com.solution.grapeApp.repositories.EmployeeRepository;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> optional = employeeRepository.findEmployeeByUsername(username);
        return optional.get();
    }

}
