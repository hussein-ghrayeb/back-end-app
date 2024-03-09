package com.solution.grapeApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.solution.grapeApp.entities.Product;
import com.solution.grapeApp.entities.responses.CustomerFavoriteProduct;
import com.solution.grapeApp.services.CustomerFavoriteProductService;

@RestController
@RequestMapping("/api/favoriteProduct")
public class CustomerFavoriteProductController {

    @Autowired
    CustomerFavoriteProductService customerFavoriteProductService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<CustomerFavoriteProduct>> getAllProducts() {
        return ResponseEntity.ok(customerFavoriteProductService.getAll());
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Void> deleteProduct(@RequestParam String customerId, @RequestParam String productId) {
        try {
            if (customerFavoriteProductService.getCustomerFavoriteProductsByIDs(customerId, productId).size() > 0) {
                customerFavoriteProductService.deleteProduct(customerId, productId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<CustomerFavoriteProduct> saveProduct(@RequestBody CustomerFavoriteProduct product) {
        try {
            CustomerFavoriteProduct savedProduct = customerFavoriteProductService.saveCustomerFavoriteProduct(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
