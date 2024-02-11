package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.FxyDesc;
import com.solution.grapeApp.repositories.FxyDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FxyDescService {

    @Autowired
    private FxyDescRepository repository;

    public void saveFxyDesc(FxyDesc fxyDesc){
        repository.save(fxyDesc);
    }

    public FxyDesc findByNameAndModel(String name,String model){
        return repository.findByNameAndModel(name,model);
    }

}
