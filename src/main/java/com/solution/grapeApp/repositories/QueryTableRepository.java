package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.QueryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryTableRepository extends JpaRepository<QueryTable,String>{
    public QueryTable findByName(String name);
}
