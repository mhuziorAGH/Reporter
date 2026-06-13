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

        Employee kowalski = new Employee("Kowalski Jan");
        Employee nowak = new Employee("Nowak Piotr");
        Project projekt1 = new Project("Projekt1");
        Project projekt2 = new Project("Projekt2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 1, 1), Duration.ofHours(8), kowalski, projekt1));
        tasks.add(new Task("Task2", LocalDate.of(2026, 1, 2), Duration.ofHours(6), kowalski, projekt2));
        tasks.add(new Task("Task3", LocalDate.of(2026, 1, 1), Duration.ofHours(4), nowak, projekt1));

        EmployeeReport report = EmployeeReport.generateReport(tasks);
        EmployeeReportPrinter.printReport(report);

        ProjectReport projectReport = ProjectReport.generateReport(tasks);
        ProjectReportPrinter.printReport(projectReport);
    }
}