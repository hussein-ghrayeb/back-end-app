package com.solution.grapeApp.entities;

import com.solution.grapeApp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "details")
    private String details;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "in_store_pickup")
    private Boolean inStorePickup;

    @Column(name = "delivery_instruction")
    private String deliveryInstruction;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

}
