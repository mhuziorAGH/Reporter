package org.example.data;
import org.example.cli.CLI;
import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;

import org.apache.poi.ss.usermodel.*;
import org.example.output.ErrorsReport;

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
        List<String> errors = new ArrayList<>();

        File file = new File(filePath);

        //if (!validateInputExcelFile(file, errors)) {
        //    return new ExcelReadResult(result, errors);
        //}
        //validateInputExcelFile(file);

        String str_employee = file.getName()
                .replaceFirst("[.][^.]+$", "")
                .replace("_", " ");

        Employee employee = new Employee(str_employee);

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {

                Sheet sheet = workbook.getSheetAt(sheetIndex);

                Project project = new Project(sheet.getSheetName());

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

                    Row row = sheet.getRow(rowIndex);
                    //Column column = sheet.getColumn

                    try {


                        Cell dataCell = row.getCell(0);
                        Cell taskCell = row.getCell(1);
                        Cell timeCell = row.getCell(2);

                        if (dataCell == null || taskCell == null || timeCell == null) {
                            throw new RuntimeException("No data provided in the file " + file.getName() + "in sheet" + sheet +
                                    "', in row: " + (rowIndex + 1));
                        }

                        LocalDate date = convertDate(dataCell);
                        String description = convertString(taskCell);
                        Duration time = convertDuration(timeCell);

                        //LocalDate date = convertDate(dataCell);
                        //String description = taskCell.getStringCellValue();

                        //double hours = timeCell.getNumericCellValue();
                        //Duration time = Duration.ofMinutes(Math.round(hours * 60));

                        Task task = new Task(description, date, time, employee, project);

                        result.add(task);
                    } catch (Exception e) {
                        errors.add(
                                file.getName() + "w arkuszu" + sheet +
                                        "', wiersz Excel: " + (rowIndex + 1) +
                                        " -> " + e.getMessage()
                        );
                    }
                }
            }

        }
        catch (IOException e) {
            throw new RuntimeException("Can not read Excel file", e);
        }
        //catch (Exception e) {
        //    throw new RuntimeException("Can not read Excel file", e);
        //}

        ErrorsReport errorsReport = new ErrorsReport();

        errorsReport.saveToFile(errors);

        return result;
    }

    private boolean validateInputExcelFile(File file, List<String> errors) {
        if (!file.exists()) {
            errors.add("File " + file.getPath() + "doesn't exist");
            return false;
        }

        if (!file.isFile()) {
            errors.add("Provided path is not a file: " + file.getPath());
            return false;
        }

        String fileName = file.getName().toLowerCase();
        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
            errors.add("Inapropriate input file format. Required input file formats .xls lub .xlsx");
            return false;
        }
        return true;
    }

    private LocalDate convertDate(Cell cell) {
        if (cell.getCellType() != CellType.NUMERIC) {
            throw new IllegalArgumentException("Date must be a number date Excela");
        }
        return DateUtil.getJavaDate(cell.getNumericCellValue())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private String convertString(Cell cell) {

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        }

        throw new IllegalArgumentException("Task must be a string");
    }

    private Duration convertDuration(Cell cell) {

        double hours;

        if (cell.getCellType() == CellType.NUMERIC) {
            hours = cell.getNumericCellValue();

        } else if (cell.getCellType() == CellType.STRING) {

            String value = cell.getStringCellValue()
                    .trim()
                    .replace(",", ".");

            try {
                hours = Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Time must be a valid number");
            }

        } else {
            throw new IllegalArgumentException("Time must be a number");
        }

        if (Double.isNaN(hours) || Double.isInfinite(hours)) {
            throw new IllegalArgumentException("Time must be a finite number");
        }

        if (hours < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

        return Duration.ofMinutes(Math.round(hours * 60));
    }

}
