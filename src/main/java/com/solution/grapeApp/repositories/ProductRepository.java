package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
     List<Product> findProductsByCategoryId(String categoryId);

     @Query(value = "Select p From Product p Where p.arabicName like %:name% Or p.englishName like %:name% ")
     List<Product> findProductsByName(@Param("name") String name);
}
