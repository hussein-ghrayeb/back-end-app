package com.solution.grapeApp.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solution.grapeApp.entities.Promotion;

import jakarta.transaction.Transactional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE promotions SET is_default_promotion = CASE id WHEN :id then true ELSE false END", nativeQuery = true)
    public void setAsDefault(String id);

    @Query(value = "SELECT p.* FROM promotions p WHERE (NOW() BETWEEN p.valid_from AND p.valid_to) OR p.valid_to is NULL", nativeQuery = true)
    public List<Promotion> findValidPromotions();

    @Query(value = "SELECT p.* FROM promotions p WHERE p.is_default_promotion = true AND (NOW() BETWEEN p.valid_from AND p.valid_to) OR p.valid_to is NULL", nativeQuery = true)
    public Optional<Promotion> findDefaultPromotion();
}
