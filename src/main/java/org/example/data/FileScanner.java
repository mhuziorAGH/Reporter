package org.example.data;
import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

public class FileScanner {

    public List<Task> readExcelFile(String filePath) {

        List<Task> result = new ArrayList<>();

        File file = new File(filePath);
        String str_employee = file.getName()
                .replaceFirst("[.][^.]+$", "")
                .replace("_", " ");

        Employee employee = new Employee(str_employee);

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {

                Sheet sheet = workbook.getSheetAt(sheetIndex);

                Project project = new Project(sheet.getSheetName());

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

                    Row row = sheet.getRow(rowIndex);

                    Cell dataCell = row.getCell(0);
                    Cell taskCell = row.getCell(1);
                    Cell timeCell = row.getCell(2);

                    LocalDate date = convertDate(dataCell);
                    String description = taskCell.getStringCellValue();

                    double hours = timeCell.getNumericCellValue();
                    Duration time = Duration.ofMinutes(Math.round(hours * 60));

                    Task task = new Task(description, date, time, employee, project);

                    result.add(task);
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
