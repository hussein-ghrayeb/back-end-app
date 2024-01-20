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
@Table(name="restaurant_type")
public class RestaurantType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="ar_name")
    private String arabicName;

    @Column(name="en_name")
    private String englishName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurantType",fetch =FetchType.EAGER)
    private List<Restaurant> restaurants;
}
