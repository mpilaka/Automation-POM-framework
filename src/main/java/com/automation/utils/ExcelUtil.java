/**
 * File: src/main/java/com/cms/utils/ExcelUtil.java
 * Description: Utility for reading test data from Excel files
 */
package com.cms.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private static final String TEST_DATA_PATH = ConfigManager.getInstance().getProperty("testdata.path");

    public static List<Map<String, String>> getTestData(String fileName, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(TEST_DATA_PATH + fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            // Get all column names
            List<String> columnNames = new ArrayList<>();
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                columnNames.add(headerRow.getCell(i).getStringCellValue());
            }

            // Iterate through each row and create a map of column name and value
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = new HashMap<>();

                for (int j = 0; j < columnNames.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(columnNames.get(j), getCellValueAsString(cell));
                }
                testData.add(rowData);
            }
        } catch (IOException e) {
            LoggerUtil.getLogger().error("Error reading Excel file: " + e.getMessage());
        }
        return testData;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                } else {
                    // Convert numeric to string without decimal if it's a whole number
                    double value = cell.getNumericCellValue();
                    if (value == Math.floor(value)) {
                        return String.valueOf((int) value);
                    }
                    return String.valueOf(value);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
