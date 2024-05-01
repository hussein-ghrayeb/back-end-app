package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT u.* FROM users u WHERE u.phone_number = :username", nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String username);
}
