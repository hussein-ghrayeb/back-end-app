package com.solution.grapeApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "is_default", columnDefinition = "tinyint(1) default 1")
    private Boolean isDefault;

    @Column(name = "address_area")
    private String addressArea;

    @Column(name = "additional_info")
    private String additionalInfo;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
