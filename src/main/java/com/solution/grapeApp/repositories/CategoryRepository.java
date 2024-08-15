package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Category;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(value = "SELECT c.* FROM categories c WHERE c.en_name = :categoryName", nativeQuery = true)
    public Optional<Category> findCategoryByName(String categoryName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE categories SET en_name = :categoryName where id = :categoryId", nativeQuery = true)
    public void uodateCategoryName(String categoryId, String categoryName);
}
