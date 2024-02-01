package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
     List<Product> findProductsByCategoryId(String categoryId);
}
