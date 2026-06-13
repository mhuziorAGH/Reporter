package org.example;

import org.example.data.FileScanner;
import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;
import org.example.output.EmployeeReportPrinter;
import org.example.output.ProjectReportPrinter;
import org.example.service.EmployeeReport;
import org.example.service.ProjectReport;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        FileScanner fileScanner = new FileScanner();
        List<Task> tasks = fileScanner.readExcelFile("Resources/reporter-dane/2012/01/Kowalski_Jan.xls");

        EmployeeReport report = EmployeeReport.generateReport(tasks, LocalDate.of(2010, 1, 1), LocalDate.of(2030, 1, 1));
        EmployeeReportPrinter.printReport(report);
    }
}