package org.example;

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

        // mock data dla EmployeeReport
//        Employee kowalski = new Employee("Kowalski Jan");
//        Employee nowak = new Employee("Nowak Piotr");
//        Project project = new Project("Projekt1");
//
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Task1", LocalDate.of(2026, 1, 1), Duration.ofHours(8), kowalski, project));
//        tasks.add(new Task("Task2", LocalDate.of(2026, 1, 2), Duration.ofHours(6), kowalski, project));
//        tasks.add(new Task("Task3", LocalDate.of(2026, 1, 1), Duration.ofHours(4), nowak, project));
//
//        EmployeeReport report = EmployeeReport.generateReport(tasks);
//        EmployeeReportPrinter.printReport(report);
//
//        ProjectReport projectReport = ProjectReport.generateReport(tasks);
//        ProjectReportPrinter.printReport(projectReport);
    }
}