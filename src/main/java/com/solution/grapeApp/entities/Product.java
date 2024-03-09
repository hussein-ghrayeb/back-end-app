package com.solution.grapeApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solution.grapeApp.entities.responses.CustomerFavoriteProduct;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "ar_name")
    private String arabicName;

    @Column(name = "en_name")
    private String englishName;

    @Column(name = "ar_desc")
    private String arabicDescription;

    @Column(name = "en_desc")
    private String englishDescription;

    @Column(name = "price")
    private Double price;

    @Column(name = "shelf_available")
    private Float shelfAvailable;

    @Column(name = "stock_available")
    private Float stockAvailable;

    @Column(name = "is_favorite")
    private Boolean isFavorite = false;

    @Column(name = "is_out_of_stock")
    private Boolean isOutOfStock;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnore
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    @JsonIgnore
    private List<OrderProduct> ordersProducts = new ArrayList();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    @JsonIgnore
    private List<CustomerFavoriteProduct> customerFavoriteProducts = new ArrayList();
}
