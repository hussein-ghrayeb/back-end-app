package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.Product;
import com.solution.grapeApp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public List<Product> getAllProducts(){
        return  repository.findAll();
    }
    public Optional<Product> getProductById(String id){
        return  repository.findById(id);
    }

    public List<Product> getProductsByCategory(String categoryId){
        return repository.findProductsByCategoryId(categoryId);
    }
    public Product saveProduct(Product  product){
        return repository.save(product);
    }
    public void deleteProductById(String id){
        repository.deleteById(id);
    }
    public Boolean isProductExists(String id){
        return repository.existsById(id);
    }

    public List<Product> findProductsByName(String name){
        return  repository.findProductsByName(name);
    }


}
