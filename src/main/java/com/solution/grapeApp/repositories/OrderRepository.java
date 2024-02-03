package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {

    public List<Order> findOrderByCustomerId(String customerId);

}
