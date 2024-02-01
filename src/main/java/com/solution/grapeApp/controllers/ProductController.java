package com.solution.grapeApp.controllers;


import com.solution.grapeApp.entities.Product;
import com.solution.grapeApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllItProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/getProductById")
    public ResponseEntity<Product> getProductsById(@RequestParam String id) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/getProductsByCategoryId")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestParam String categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        try {
             Product savedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/deleteProduct")
    public ResponseEntity<Void> deleteProduct(@RequestParam String id) {
        try {
            if (productService.isProductExists(id)) {
                productService.deleteProductById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
