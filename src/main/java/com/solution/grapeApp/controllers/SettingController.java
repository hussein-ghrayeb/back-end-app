package com.solution.grapeApp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solution.grapeApp.entities.Employee;
import com.solution.grapeApp.entities.Setting;
import com.solution.grapeApp.repositories.SettingRepository;
import com.solution.grapeApp.services.SettingService;

@RestController
@RequestMapping("/api/setting")
public class SettingController {

    @Autowired
    SettingService settingService;

    @Autowired
    SettingRepository repository;

    @PostMapping("/saveSetting")
    public ResponseEntity<Setting> saveSetting(@RequestBody Setting setting) {
        try {
            Setting savedSetting = settingService.saveSetting(setting);
            return ResponseEntity.ok(savedSetting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getSettingByName")
    public ResponseEntity<Setting> getSettingById(@RequestParam String name) {
        Optional<Setting> optionalSetting = repository.findSettingByName(name);

        if (optionalSetting.isPresent()) {
            Setting setting = optionalSetting.get();
            return ResponseEntity.ok(setting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateSetting")
    public ResponseEntity<Setting> updateSetting(@RequestBody Setting setting) {
        try {
            Optional<Setting> optional = settingService.getSettingById(setting.getId());
            if (optional.isPresent()) {
                Setting savedSetting = settingService.saveSetting(setting);
                return ResponseEntity.ok(savedSetting);
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
