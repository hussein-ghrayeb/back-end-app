package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT u.* FROM customers u WHERE u.phone_number = :username", nativeQuery = true)
    Optional<Customer> findUserByUsername(@Param("username") String username);
}
