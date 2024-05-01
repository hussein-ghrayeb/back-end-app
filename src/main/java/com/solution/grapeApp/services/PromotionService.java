package com.solution.grapeApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solution.grapeApp.entities.Promotion;
import com.solution.grapeApp.repositories.PromotionRepository;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository repository;

    public List<Promotion> getAllPromotions() {
        return repository.findAll();
    }

    public Optional<Promotion> getPromotionById(String id) {
        return repository.findById(id);
    }

    public Promotion savePromotion(Promotion promotion) {
        return repository.save(promotion);
    }

    public void deletePromotionById(String id) {
        repository.deleteById(id);
    }

    public Boolean isPromotionExists(String id) {
        return repository.existsById(id);
    }
}
