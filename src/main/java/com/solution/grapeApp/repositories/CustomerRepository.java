package com.solution.grapeApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solution.grapeApp.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
