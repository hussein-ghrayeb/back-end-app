package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.QueryTable;
import com.solution.grapeApp.repositories.QueryTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryTableService {
    @Autowired
    private QueryTableRepository repository;

    public QueryTable findByName(String tableName){
        return  repository.findByName(tableName);
    }
}
