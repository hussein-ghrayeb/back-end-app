package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Category;
import com.solution.grapeApp.entities.Product;
import com.solution.grapeApp.repositories.CategoryRepository;
import com.solution.grapeApp.repositories.ProductRepository;
import com.solution.grapeApp.services.ProductService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getProductById")
    public ResponseEntity<Product> getProductsById(@RequestParam String id) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getProductsByCategoryId")
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

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try {
            Optional<Product> optional = productService.getProductById(product.getId());
            if (optional.isPresent()) {
                Product savedProduct = productService.saveProduct(product);
                return ResponseEntity.ok(savedProduct);
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/import-products", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> importProducts(@RequestParam MultipartFile file) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"))) {

            fileReader.readLine();// try-catch omitted
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
            List<Product> productsToBeAdded = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                Optional<Category> optionalCategory = categoryRepository.findCategoryByName(csvRecord.get(0));
                if (optionalCategory.isPresent()) {
                    productsToBeAdded
                            .add(new Product(csvRecord.get(1), csvRecord.get(2), Float.parseFloat(csvRecord.get(3)),
                                    Float.parseFloat(csvRecord.get(4)), Double.parseDouble(csvRecord.get(5)),
                                    csvRecord.get(6), optionalCategory.get()));
                } else {
                    Category category = categoryRepository.save(new Category(csvRecord.get(0)));
                    productsToBeAdded
                            .add(new Product(csvRecord.get(1), csvRecord.get(2), Float.parseFloat(csvRecord.get(3)),
                                    Float.parseFloat(csvRecord.get(4)), Double.parseDouble(csvRecord.get(5)),
                                    csvRecord.get(6), category));
                }
            }

            return ResponseEntity.ok(productRepository.saveAll(productsToBeAdded));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteProduct")
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

    @GetMapping("/getProductsByName")
    public ResponseEntity<List<Product>> getAllItProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

}
