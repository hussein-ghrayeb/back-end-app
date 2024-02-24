package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    @Query(value = "Select r From Restaurant r Where r.arabicName like %:name% Or r.englishName like %:name%")
    public List<Restaurant> findRestaurantsByName(@Param("name") String name);
}
