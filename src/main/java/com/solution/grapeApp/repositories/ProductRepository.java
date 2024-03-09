package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Product;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
     @Query(value = "SELECT p.* FROM products p WHERE p.category_id = :categoryId", nativeQuery = true)
     List<Product> findProductsByCategoryId(String categoryId);

     @Modifying
     @Transactional
     @Query(value = "UPDATE products set shelf_available = shelf_available - :count , is_out_of_stock = CASE WHEN shelf_available = 1 THEN true ELSE false END WHERE id = :productId", nativeQuery = true)
     void updateProductStock(int count, String productId);

     @Query(value = "Select p From Product p Where p.arabicName like %:name% Or p.englishName like %:name% ")
     List<Product> findProductsByName(@Param("name") String name);
}
