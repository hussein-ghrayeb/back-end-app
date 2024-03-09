package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.OrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, String> {
}
