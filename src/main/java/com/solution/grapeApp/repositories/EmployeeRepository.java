package com.solution.grapeApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solution.grapeApp.entities.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "SELECT u.* FROM employees u WHERE u.phone_number = :username", nativeQuery = true)
    Optional<Employee> findEmployeeByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET is_available = :availability where id = :employeeId", nativeQuery = true)
    public void setEmployeeAvailability(@Param("employeeId") String employeeId,
            @Param("availability") boolean availability);
}
