package com.solution.grapeApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solution.grapeApp.entities.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, String> {
    @Query(value = "SELECT s.* FROM settings s WHERE s.setting_name = :name", nativeQuery = true)
    Optional<Setting> findSettingByName(@Param("name") String name);
}
