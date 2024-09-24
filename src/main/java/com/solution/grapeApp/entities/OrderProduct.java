package com.solution.grapeApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_products")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "product_count")
    private int productCount;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "shelf_number")
    private int shelfNumber;

    @Column(name = "bar_code")
    private String barCode;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @Column(name = "product_price")
    private Double productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order order;

    public OrderProduct(int count, String productName, String imageUrl, Double price, int shelfNumber, Order order,
            String barcode) {
        this.productCount = count;
        this.productName = productName;
        this.productImageUrl = imageUrl;
        this.productPrice = price;
        this.shelfNumber = shelfNumber;
        this.barCode = barcode;
        this.order = order;
    }

}
