package com.solution.grapeApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.solution.grapeApp.entities.Notification;
import com.solution.grapeApp.entities.Promotion;
import com.solution.grapeApp.repositories.NotificationRepository;
import com.solution.grapeApp.repositories.PromotionRepository;
import com.solution.grapeApp.services.PromotionService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

    @Autowired
    PromotionService promotionService;

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/getAllPromotions")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @GetMapping("/getValidPromotions")
    public ResponseEntity<List<Promotion>> getValidPromotions() {
        return ResponseEntity.ok(promotionRepository.findValidPromotions());
    }

    @GetMapping("/getPromotionById")
    public ResponseEntity<Promotion> getPromotionById(@RequestParam String id) {
        Optional<Promotion> optionalPromotion = promotionService.getPromotionById(id);

        if (optionalPromotion.isPresent()) {
            Promotion promotion = optionalPromotion.get();
            return ResponseEntity.ok(promotion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getDefaultPromotion")
    public ResponseEntity<Promotion> getDefaultPromotion() {
        Optional<Promotion> optionalPromotion = promotionRepository.findDefaultPromotion();

        if (optionalPromotion.isPresent()) {
            Promotion promotion = optionalPromotion.get();
            return ResponseEntity.ok(promotion);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/savePromotion")
    public ResponseEntity<Promotion> saveProduct(@RequestBody Promotion promotion) {
        try {
            Promotion savedPromotion = promotionService.savePromotion(promotion);
            if (savedPromotion.getId() != null)
                notificationRepository.save(new Notification("Special Offer", savedPromotion));
            return ResponseEntity.ok(savedPromotion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updatePromotion")
    public ResponseEntity<Promotion> updatePromotion(@RequestBody Promotion promotion) {
        try {
            Optional<Promotion> optional = promotionService.getPromotionById(promotion.getId());
            if (optional.isPresent()) {
                Promotion savedPromotion = promotionService.savePromotion(promotion);
                return ResponseEntity.ok(savedPromotion);
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deletePromotion")
    public ResponseEntity<Void> deletePromotion(@RequestParam String id) {
        try {
            if (promotionService.isPromotionExists(id)) {
                promotionService.deletePromotionById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/setAsDefaultPromotion")
    public ResponseEntity<Void> setAsDefaultPromotion(@RequestParam String id) {
        try {
            if (promotionService.isPromotionExists(id)) {
                promotionRepository.setAsDefault(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
