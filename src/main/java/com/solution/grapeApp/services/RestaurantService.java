package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.Restaurant;
import com.solution.grapeApp.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        return repository.findById(id);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public void deleteRestaurantById(String id) {
        repository.deleteById(id);
    }

    public Boolean isRestaurantExists(String id) {
        return repository.existsById(id);
    }

    public List<Restaurant> findRestaurantsByName(String name) {
        return repository.findRestaurantsByName(name);
    }

}
