package com.solution.grapeApp.Model;

import com.solution.grapeApp.entities.Fxy;
import com.solution.grapeApp.entities.QueryColumn;
import com.solution.grapeApp.entities.QueryTable;
import com.solution.grapeApp.services.DynamicService;
import com.solution.grapeApp.services.FxyService;
import com.solution.grapeApp.services.QueryTableService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ExcelGenerator {

    @Autowired
    private FxyService fxyService;

    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private QueryTableService queryTableService;

    private String getQuery(String tableName){
        List<String> selectedColumnsList=new ArrayList<>();

        QueryTable queryTable =queryTableService.findByName(tableName);

        List<QueryColumn> columnsList = queryTable.getColumnsList();
        List<Fxy> columnsFxyList = fxyService.getTableDisplayColumn(tableName)
                                                .stream()
                                                .filter(column -> "O".equals(column.getY3()))
                                                .toList();
        for (QueryColumn queryColumn : columnsList){
            for (Fxy fxy : columnsFxyList) {
                if(queryColumn.getName().equals(fxy.getX3())){
                    selectedColumnsList.add(queryColumn.getContent()+ " "+fxy.getY1());
                }
            }
        }
         return  "Select " + String.join(",", selectedColumnsList) + " From " + queryTable.getTables() + " Where " + queryTable.getCondition() ;
    }

    private List<Map<String,Object>> getObjectList(String tableName,Map<String,String> filteredColumns){
        String query = getQuery(tableName);
        for (Map.Entry<String,String> filteredColumn : filteredColumns.entrySet()) {
            query =query.replace("#"+filteredColumn.getKey()+"#",filteredColumn.getValue());
        }
        try {
            return  dynamicService.executeDynamicSql(query);
        }catch (Exception e){
            return null;
        }
    }
    public List<byte[]> generateFile(String tableName,Map<String,String> filteredColumns) {

        List<Map<String,Object>> objectList =getObjectList(tableName,filteredColumns);
        int cellNumber =0;
        int rowNumber  =0;
        Row row;
        List<byte[]> excelBytesList = new ArrayList<>();

        if (objectList!=null && !objectList.isEmpty()) {

            try {
                Workbook workbook = new XSSFWorkbook();

                // Create a bold font with white color
                Font boldFont = workbook.createFont();
                boldFont.setBold(true);
                boldFont.setColor(IndexedColors.WHITE.getIndex());

                // Create a cell style with the bold font and black background
                CellStyle boldStyle = workbook.createCellStyle();
                boldStyle.setFont(boldFont);
                boldStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
                boldStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                // Get the first map in the list
                Map<String, Object> firstMap = objectList.get(0);

                Sheet sheet = workbook.createSheet("Sheet1");

                row = sheet.createRow(rowNumber);

                for (String key : firstMap.keySet()) {
                    Cell cell = row.createCell(cellNumber);
                    cell.setCellValue(key);
                    cell.setCellStyle(boldStyle);
                    cellNumber++;
                }

                for (Map<String,Object> map : objectList){
                    cellNumber = 0;
                    rowNumber ++;
                    row = sheet.createRow(rowNumber);
                    for (Object columns : map.values()) {
                        row.createCell(cellNumber).setCellValue(String.valueOf(columns));
                        cellNumber++;
                    }
                }

                try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                    workbook.write(byteArrayOutputStream);
                    excelBytesList.add(byteArrayOutputStream.toByteArray());
                }
                workbook.close();
                System.out.println("Excel file has been generated successfully.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return excelBytesList;
    }
}
