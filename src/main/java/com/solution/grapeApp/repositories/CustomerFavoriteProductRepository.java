package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.responses.CustomerFavoriteProduct;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFavoriteProductRepository extends JpaRepository<CustomerFavoriteProduct, String> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers_favorite_products WHERE product_id = :productId and customer_id = :customerId", nativeQuery = true)
    void deleteCustomerFavoriteProduct(String customerId, String productId);

    @Query(value = "SELECT * FROM customers_favorite_products WHERE product_id = :productId and customer_id = :customerId", nativeQuery = true)
    List<CustomerFavoriteProduct> getCustomerFavoriteProduct(String customerId, String productId);
}
