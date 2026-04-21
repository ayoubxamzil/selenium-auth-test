package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static Object[][] getData(String filePath, String sheetName) {
        List<Object[]> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null || isRowEmpty(row)) continue;
                int nbCols = row.getLastCellNum();
                Object[] rowData = new Object[nbCols];

                for (int j = 0; j < nbCols; j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = formatter.formatCellValue(cell).trim();
                }

                rows.add(rowData);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return rows.toArray(new Object[0][]);
    }

    private static boolean isRowEmpty(Row row) {
        for (int c = 0; c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}