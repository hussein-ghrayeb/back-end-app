package com.solution.grapeApp.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="query_table")
public class QueryTable {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name="tables")
    private String tables;

    @Column(name="where_condition",length = 1000)
    private String condition;

    @OneToMany(mappedBy = "queryTable",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QueryColumn> columnsList;

}
