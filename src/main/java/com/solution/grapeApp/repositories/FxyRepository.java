package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.entities.Fxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FxyRepository extends JpaRepository<Fxy, String> {

    @Query(value = "SELECT f FROM Fxy f WHERE f.name = :name And f.model = :model " +
            "And (:x1 Is Null Or f.x1 = :x1) " +
            "And (:x2 Is Null Or f.x2 = :x2) " +
            "And (:x3 Is Null Or f.x3 = :x3) " +
            "And (:y1 Is Null Or f.y1 = :y1) " +
            "And (:y2 Is Null Or f.y2 = :y2) " +
            "And (:y3 Is Null Or f.y3 = :y3) ")
    List<Fxy> findFxy(@Param("name") String name, @Param("model") String model,
            @Param("x1") String x1, @Param("x2") String x2,
            @Param("x3") String x3, @Param("y1") String y1,
            @Param("y2") String y2, @Param("y3") String y3);
}
