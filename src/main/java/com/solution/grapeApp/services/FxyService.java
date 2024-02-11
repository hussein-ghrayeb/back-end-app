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

    public List<Fxy> getFxyList(String name,String model,String x1,String x2,String x3){
        return  repository.findFxy(name,model,x1,x2,x3,null,null,null);
    }
    public List<Fxy> getTableDisplayColumn(String tableName){
        return  repository.findFxy("EXCEL","GENERATION","COLUMN",tableName,null,null,null,null);
    }
    public void saveAll(List<Fxy> fxyList){
        repository.saveAll(fxyList);
    }

}
