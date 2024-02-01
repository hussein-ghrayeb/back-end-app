package com.solution.grapeApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="barcode")
    private String barcode;

    @Column(name="ar_name")
    private String arabicName;

    @Column(name="en_name")
    private String englishName;

    @Column(name="ar_desc")
    private String arabicDescription;

    @Column(name="en_desc")
    private String englishDescription;

    @Column(name="price")
    private Double price;

    @Column(name="shelf_available")
    private Float shelfAvailable;

    @Column(name="stock_available")
    private Float stockAvailable;

    @Column(name="is_favorite")
    private Boolean isFavorite;

    @Column(name="image_url")
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();
}
