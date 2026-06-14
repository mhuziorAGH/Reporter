package org.example.service;

import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeReportTest {

    //1 PRACOWNIK, dwa zadania w tym samym projekcie + suma
    @Test
    public void test1EmployeeeReportService() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Project1");
        Task task1 = new Task("Task1", LocalDate.of(2026, 2, 1), Duration.ofHours(1), employee, project);
        Task task2 = new Task("Task2", LocalDate.of(2026, 2, 1), Duration.ofHours(2), employee, project);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        assertTrue(report.employeeProjectHours.containsKey(employee));
        assertEquals(Duration.ofHours(3), report.employeeProjectHours.get(employee).get(project));
    }

    //2 pracownikow
    public void testTwoEmployees() {
        Employee kowalski = new Employee("Kowalski Jan");
        Employee nowak = new Employee("Nowak Piotr");
        Project project = new Project("Project1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 2, 1), Duration.ofHours(4), kowalski, project));
        tasks.add(new Task("Task2", LocalDate.of(2026, 2, 1), Duration.ofHours(6), nowak, project));

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        assertEquals(Duration.ofHours(4), report.employeeProjectHours.get(kowalski).get(project));
        assertEquals(Duration.ofHours(6), report.employeeProjectHours.get(nowak).get(project));
    }

    //2pracowników, wiele zadań każdy
    @Test
    public void testTwoEmployeesMultipleTasks() {
        Employee kowalski = new Employee("Kowalski Jan");
        Employee nowak = new Employee("Nowak Piotr");
        Project project = new Project("Project1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 2, 1), Duration.ofHours(2).plus(Duration.ofMinutes(30)), kowalski, project));
        tasks.add(new Task("Task2", LocalDate.of(2026, 2, 2), Duration.ofHours(3), kowalski, project));
        tasks.add(new Task("Task3", LocalDate.of(2026, 2, 1), Duration.ofHours(5).plus(Duration.ofMinutes(30)), nowak, project));
        tasks.add(new Task("Task4", LocalDate.of(2026, 2, 3), Duration.ofHours(1), nowak, project));

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        assertEquals(Duration.ofMinutes(330), report.employeeProjectHours.get(kowalski).get(project));
        assertEquals(Duration.ofMinutes(390), report.employeeProjectHours.get(nowak).get(project));
    }

    // pusta lista → pusty raport
    @Test
    public void testEmptyList() {
        List<Task> tasks = new ArrayList<>();

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        assertTrue(report.employeeProjectHours.isEmpty());
    }

    //ddokladnie 1 proacwnik
    @Test
    public void testReportContainsExactlyTheRightEmployees() {
        Employee kowalski = new Employee("Kowalski Jan");
        Project project = new Project("Project1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 2, 1), Duration.ofHours(8), kowalski, project));

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        assertEquals(1, report.employeeProjectHours.size());
    }
}