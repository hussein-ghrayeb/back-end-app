package com.solution.grapeApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solution.grapeApp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(name = "details")
    private String details;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Column(name = "delivery_instruction")
    private String deliveryInstruction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderProduct> products = new ArrayList();

    public Order(double totalPrice, String details, String code, String deliveryInstruction, Customer customer,
            Address address) {
        this.address = address;
        this.totalPrice = totalPrice;
        this.code = code;
        this.customer = customer;
        this.details = details;
        this.deliveryInstruction = deliveryInstruction;
    }

}
