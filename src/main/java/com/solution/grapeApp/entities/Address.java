package com.solution.grapeApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="item")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // to discuss address attribure givem from google maps

    @Column(name = "is_main")
    private Boolean isMain;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Restaurant restaurant;

}
