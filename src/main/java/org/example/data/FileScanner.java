package org.example.data;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {

    public List<List<Object>> readExcelFile(String filePath) {

        List<List<Object>> result = new ArrayList<>();

        File file = new File(filePath);
        String fileName = file.getName()
                .replaceFirst("[.][^.]+$", "")
                .replace("_", " ");

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {

                Sheet sheet = workbook.getSheetAt(sheetIndex);

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

                    Row row = sheet.getRow(rowIndex);

                    Cell dataCell = row.getCell(0);
                    Cell taskCell = row.getCell(1);
                    Cell timeCell = row.getCell(2);

                    LocalDate date = convertDate(dataCell);
                    String task = taskCell.getStringCellValue();
                    Double time = timeCell.getNumericCellValue();

                    List<Object> record = new ArrayList<>();

                    record.add(fileName);
                    record.add(date);
                    record.add(task);
                    record.add(time);
                    result.add(record);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can not read file", e);
        }

        return result;
    }

    private LocalDate convertDate(Cell cell) {
        return DateUtil.getJavaDate(cell.getNumericCellValue())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
