package com.solution.grapeApp.entities;

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
@Table(name="item")
public class Item {

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
    private Integer shelfAvailable;

    @Column(name="stock_available")
    private Integer stockAvailable;

    @Column(name="discount")
    private Double discount;

    @Column(name="weight")
    private Double weight;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    private Menu menu;

    @ManyToMany(mappedBy = "items",fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    @ManyToMany(mappedBy = "items",fetch = FetchType.LAZY)
    private Set<Offer> offers = new HashSet<>();


//    @ManyToOne(fetch = FetchType.LAZY)
//    private Offer offer;

}
