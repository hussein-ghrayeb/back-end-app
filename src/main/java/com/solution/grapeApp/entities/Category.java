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
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="ar_name")
    private String arabicName;

    @Column(name="en_name")
    private String englishName;

    @Column(name="ar_desc")
    private String arabicDescription;

    @Column(name="en_desc")
    private String englishDescription;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

}