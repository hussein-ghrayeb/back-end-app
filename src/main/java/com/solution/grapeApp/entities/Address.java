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
    
    @Column(name = "longitude")
    private double longitude;
    
    @Column(name = "latitude")
    private double latitude;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "address_area")
    private String addressArea;

    @Column(name = "additonal_info")
    private String additionalInfo;
    
    @ManyToOne
    private Customer customer;
}
