package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.FxyDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FxyDescRepository extends JpaRepository<FxyDesc, String> {
    @Query(value = "Select f from FxyDesc f Where (:name Is Null Or f.name = :name ) And (:model Is Null Or f.model = :model )")
    public FxyDesc findByNameAndModel(@Param("name") String name, @Param("model") String model);
}
