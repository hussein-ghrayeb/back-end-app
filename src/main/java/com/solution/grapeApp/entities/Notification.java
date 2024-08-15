package com.solution.grapeApp.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "is_read")
    private boolean isRead = false;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    public Notification(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
