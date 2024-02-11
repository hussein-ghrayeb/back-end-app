package com.solution.grapeApp.entities.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFxyListRequest {
    private String name;
    private String model;
    private String x1;
    private String x2;
    private String x3;
}
