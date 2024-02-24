package com.solution.grapeApp.entities;

import com.solution.grapeApp.enums.DaysOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "ar_name")
    private String arabicName;

    @Column(name = "en_name")
    private String englishName;

    @Column(name = "ar_desc")
    private String arabicDescription;

    @Column(name = "en_desc")
    private String englishDescription;

    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @ElementCollection(targetClass = DaysOfWeek.class)
    @CollectionTable(name = "restaurant_closing_days", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "closing_day")
    private Set<DaysOfWeek> closingDays;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

}
