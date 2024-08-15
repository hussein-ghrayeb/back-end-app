package com.solution.grapeApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solution.grapeApp.entities.Employee;
import com.solution.grapeApp.entities.Setting;
import com.solution.grapeApp.repositories.SettingRepository;

@Service
public class SettingService {

    @Autowired
    private SettingRepository repository;

    public Setting saveSetting(Setting setting) {
        return repository.save(setting);
    }

    public Optional<Setting> getSettingById(String id) {
        return repository.findById(id);
    }
}
