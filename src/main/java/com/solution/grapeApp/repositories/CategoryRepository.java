package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
