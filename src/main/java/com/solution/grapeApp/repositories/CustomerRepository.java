package com.solution.grapeApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solution.grapeApp.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT u.* FROM customers u WHERE u.phone_number = :username", nativeQuery = true)
    Optional<Customer> findCustomerByUsername(@Param("username") String username);
}
