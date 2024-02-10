package com.solution.grapeApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="query_column")
public class QueryColumn {

    @Id
    @Column(name = "name")
    private String name;

    @Id
    @ManyToOne
    @JoinColumn(name="query_table")
    @JsonIgnore
    private QueryTable queryTable;

    @Column(name = "content")
    private String content;

    @Column(name = "active")
    private Boolean active;


}
