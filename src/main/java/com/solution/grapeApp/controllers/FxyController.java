package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Fxy;
import com.solution.grapeApp.entities.FxyDesc;
import com.solution.grapeApp.entities.Order;
import com.solution.grapeApp.entities.requests.GetFxyDescRequest;
import com.solution.grapeApp.entities.requests.GetFxyListRequest;
import com.solution.grapeApp.services.FxyDescService;
import com.solution.grapeApp.services.FxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fxy")
public class FxyController {

    @Autowired
    private FxyService fxyService;

    @Autowired
    private FxyDescService fxyDescService;
    @PostMapping("/getFxyList")
    public ResponseEntity<List<Fxy>> getFxyList(@RequestBody GetFxyListRequest request) {
        return ResponseEntity.ok(fxyService.getFxyList(request.getName(),request.getModel(),request.getX1(),request.getX2(),request.getX3()));
    }

    @PostMapping("/saveFxyList")
    public ResponseEntity<Void> saveOrder(@RequestBody List<Fxy> fxyList) {
        try {
            fxyService.saveAll(fxyList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/getFxyDesc")
    public ResponseEntity<FxyDesc> getFxyDesc(@RequestBody GetFxyDescRequest request) {
        return ResponseEntity.ok(fxyDescService.findByNameAndModel(request.getName(),request.getModel()));
    }

    @PostMapping("/saveFxyDesc")
    public ResponseEntity<Void> saveFxyDesc(@RequestBody FxyDesc fxyDesc) {
        try {
            fxyDescService.saveFxyDesc(fxyDesc);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
