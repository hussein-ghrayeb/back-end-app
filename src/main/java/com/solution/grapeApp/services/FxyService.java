package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.Fxy;
import com.solution.grapeApp.repositories.FxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FxyService {
    @Autowired
    private FxyRepository  repository;

    public List<Fxy> getTableDisplayColumn(String tableName){
        return  repository.findFxy("EXCEL","GENERATION","COLUMN",tableName,null,null,null,null);
    }
}
