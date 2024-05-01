package com.solution.grapeApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FileDTO {
    private String fileName;
    private String extension;
    private String file;

}
