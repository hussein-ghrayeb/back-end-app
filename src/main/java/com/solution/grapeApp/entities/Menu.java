package com.solution.grapeApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "menu")
    private List<Item> items;

    @OneToMany(mappedBy = "menu")
    private List<Offer> offers;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
