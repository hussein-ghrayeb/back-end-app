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
@Table(name="fxy")
public class Fxy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "x1")
    private String x1;

    @Column(name = "x2")
    private String x2;

    @Column(name = "x3")
    private String x3;

    @Column(name = "y1")
    private String y1;

    @Column(name = "y2")
    private String y2;

    @Column(name = "y3")
    private String y3;
}