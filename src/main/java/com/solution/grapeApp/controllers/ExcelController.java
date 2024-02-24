package com.solution.grapeApp.controllers;

import com.solution.grapeApp.Model.ExcelGenerator;
import com.solution.grapeApp.entities.requests.GenerateExcelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/excel")
public class ExcelController {

    @Autowired
    public ExcelGenerator excelGenerator;

    @PostMapping(value = "generateExcel")
    public ResponseEntity<List<byte[]>> generateExcel(@RequestBody GenerateExcelRequest request) {
        return ResponseEntity.ok(excelGenerator.generateFile(request.getTableName(), request.getFilteredColumns()));
    }
}
