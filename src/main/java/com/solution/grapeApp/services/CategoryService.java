package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.Category;
import com.solution.grapeApp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{

    @Autowired
    private CategoryRepository repository;
    public List<Category> getAllCategories(){
        return  repository.findAll();
    }
    public Optional<Category> getCategoryById(String id){
        return  repository.findById(id);
    }

    public Category saveCategory(Category  product){
        return repository.save(product);
    }
    public void deleteCategoryById(String id){
        repository.deleteById(id);
    }
    public Boolean isCategoryExists(String id){
        return repository.existsById(id);
    }

}
