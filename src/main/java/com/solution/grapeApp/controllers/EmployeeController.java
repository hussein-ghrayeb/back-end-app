package com.solution.grapeApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.solution.grapeApp.entities.Notification;
import com.solution.grapeApp.entities.Employee;
import com.solution.grapeApp.repositories.NotificationRepository;
import com.solution.grapeApp.repositories.EmployeeRepository;
import com.solution.grapeApp.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/getEmployeeById")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam String id) {
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> saveProduct(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        try {
            Optional<Employee> optional = employeeService.getEmployeeById(employee.getId());
            if (optional.isPresent()) {
                Employee savedEmployee = employeeService.saveEmployee(employee);
                return ResponseEntity.ok(savedEmployee);
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<Void> deleteEmployee(@RequestParam String id) {
        try {
            if (employeeService.isEmployeeExists(id)) {
                employeeService.deleteEmployeeById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
