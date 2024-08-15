package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Category;
import com.solution.grapeApp.repositories.CategoryRepository;
import com.solution.grapeApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity<Category> getCategoriesById(@RequestParam String id) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        try {
            Optional<Category> optionalCategory = categoryService.getCategoryById(category.getId());

            if (optionalCategory.isPresent()) {
                optionalCategory.get().setEnglishName(category.getEnglishName());
                return ResponseEntity.ok(categoryService.saveCategory(optionalCategory.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.saveCategory(category);
            return ResponseEntity.ok(savedCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<Void> deleteCategory(@RequestParam String id) {
        try {
            if (categoryService.isCategoryExists(id)) {
                categoryService.deleteCategoryById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
