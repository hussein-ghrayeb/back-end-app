package com.solution.grapeApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solution.grapeApp.entities.Notification;
import com.solution.grapeApp.repositories.NotificationRepository;

@RestController
@RequestMapping("api/notification")
public class NotificationController {

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/getAllNotifications")
    public ResponseEntity<List<Notification>> getAllCategories() {
        return ResponseEntity.ok(notificationRepository.findAll());
    }

    @PutMapping("/setAsRead")
    public ResponseEntity<?> setAsReaded(@RequestParam String id) {
        try {
            Optional<Notification> optional = notificationRepository.findById(id);

            if (optional.isPresent()) {
                notificationRepository.setAsReaded(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getUnReadedNotificationsCount")
    public ResponseEntity<?> getUnReadedNotifications() {
        return ResponseEntity.ok(notificationRepository.getCountOfNotReadedNotifications());
    }
}
