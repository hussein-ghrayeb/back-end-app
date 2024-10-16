package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.Order;
import com.solution.grapeApp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public List<Order> getCreatedOrders() {
        return repository.findCreatedOrders();
    }

    public List<Order> getPackedOrders() {
        return repository.findPackedOrders();
    }

    public Optional<Order> getOrderById(String id) {
        return repository.findById(id);
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    public void deleteOrderById(String id, String status) {
        repository.setOrderstatus(id, status);
    }

    public Boolean isOrderExists(String id) {
        return repository.existsById(id);
    }

    public List<Order> findOrdersByCustomerId(String customerId) {
        return repository.findOrderByCustomerId(customerId);
    }
}
