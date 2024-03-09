package com.solution.grapeApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solution.grapeApp.entities.responses.CustomerFavoriteProduct;
import com.solution.grapeApp.repositories.CustomerFavoriteProductRepository;

@Service
public class CustomerFavoriteProductService {

    @Autowired
    private CustomerFavoriteProductRepository customerFavoriteProductRepository;

    public CustomerFavoriteProduct saveCustomerFavoriteProduct(CustomerFavoriteProduct customerFavoriteProduct) {
        return customerFavoriteProductRepository.save(customerFavoriteProduct);
    }

    public List<CustomerFavoriteProduct> getAll() {
        return customerFavoriteProductRepository.findAll();
    }

    public void deleteProduct(String customerId, String productId) {
        customerFavoriteProductRepository.deleteCustomerFavoriteProduct(customerId, productId);
    }

    public List<CustomerFavoriteProduct> getCustomerFavoriteProductsByIDs(String customerId, String productId) {
        return customerFavoriteProductRepository.getCustomerFavoriteProduct(customerId, productId);
    }
}
