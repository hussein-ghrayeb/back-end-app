package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Restaurant;
import com.solution.grapeApp.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/getRestaurantById")
    public ResponseEntity<Restaurant> getRestaurantsById(@RequestParam String id) {
        Optional<Restaurant> optionalRestaurant = restaurantService.getRestaurantById(id);

        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/saveRestaurant")
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant) {
        try {
            Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
            return ResponseEntity.ok(savedRestaurant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/deleteRestaurant")
    public ResponseEntity<Void> deleteRestaurant(@RequestParam String id) {
        try {
            if (restaurantService.isRestaurantExists(id)) {
                restaurantService.deleteRestaurantById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getRestaurantsByName")
    public ResponseEntity<List<Restaurant>> getAllItRestaurants(@RequestParam String name) {
        return ResponseEntity.ok(restaurantService.findRestaurantsByName(name));
    }

}
