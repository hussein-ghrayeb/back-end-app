package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    public List<Order> findOrderByCustomerId(String customerId);
}
