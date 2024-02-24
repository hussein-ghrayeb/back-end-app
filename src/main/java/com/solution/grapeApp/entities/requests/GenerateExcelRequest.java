package com.solution.grapeApp.entities.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateExcelRequest {
    private String tableName;
    private Map<String, String> filteredColumns;
}
