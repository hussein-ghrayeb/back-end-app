package com.solution.grapeApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solution.grapeApp.entities.Notification;

import jakarta.transaction.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Query(value = "SELECT count(*) FROM notifications WHERE is_read = false", nativeQuery = true)
    public int getCountOfNotReadedNotifications();

    @Modifying
    @Transactional
    @Query(value = "UPDATE notifications SET is_read = true", nativeQuery = true)
    public void setAsReaded(String id);
}
