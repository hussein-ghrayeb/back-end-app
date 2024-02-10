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

    public String getTableDisplayColumn(String table){
        List<Fxy> fxyList = repository.findFxy("EXCEL","DISPLAYCOLUMNS",table,null,null,null);
        return  null;
    }
}
