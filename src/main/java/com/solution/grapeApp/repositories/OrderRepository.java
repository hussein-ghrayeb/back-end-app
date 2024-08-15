package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.entities.Order;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    public List<Order> findOrderByCustomerId(String customerId);

    @Query(value = "SELECT o.* FROM orders o WHERE o.status = 'CREATED' ORDER BY o.order_date ASC LIMIT 1", nativeQuery = true)
    List<Order> findFirstCreatedOrder();

    @Query(value = "SELECT o.* FROM orders o WHERE o.status = 'PACKED' ORDER BY o.order_date ASC LIMIT 1", nativeQuery = true)
    List<Order> findFirstPackedOrder();

    @Query(value = "SELECT o.* FROM orders o WHERE o.status = 'UNDER_PACKAGING' AND o.checked_by = :employeeId", nativeQuery = true)
    Optional<Order> findOrderUnderCheckingBy(@Param("employeeId") String employeeId);

    @Query(value = "SELECT o.* FROM orders o WHERE o.status = 'PICKUPED' AND o.delivered_by = :employeeId", nativeQuery = true)
    Optional<Order> findOrderPickupedBy(@Param("employeeId") String employeeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET status = 'PICKUPED', delivered_by = :employeeId  where id = :orderId", nativeQuery = true)
    public void setOrderAsPickuped(@Param("orderId") String orderId,
            @Param("employeeId") String employeeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET status = 'UNDER_PACKAGING', checked_by = :employeeId  where id = :orderId", nativeQuery = true)
    public void setOrderAsUnderPackaging(@Param("orderId") String orderId,
            @Param("employeeId") String employeeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET status = :status where id = :orderId", nativeQuery = true)
    public void setOrderstatus(@Param("orderId") String orderId,
            @Param("status") String status);
}
