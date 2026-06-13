package org.example.service;

import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReportTest {

    @Test
    public void test1EmployeeeReportService() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Project1");
        Task task1 = new Task("Task1", LocalDate.of(2026,02,01), Duration.ofHours(1),employee, project);
        Task task2 = new Task("Task2", LocalDate.of(2026,02,01), Duration.ofHours(2),employee, project);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);


        EmployeeReport report = EmployeeReport.generateReport(tasks);
        Assert.assertTrue(report.employees.containsKey(employee));
        Assert.assertEquals(Duration.ofHours(3), report.employees.get(employee));

    }

    // TEST 2 — dwóch pracowników
    @Test
    public void testTwoEmployees() {
        Employee kowalski = new Employee("Kowalski Jan");
        Employee nowak = new Employee("Nowak Piotr");
        Project project = new Project("Project1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 2, 1), Duration.ofHours(4), kowalski, project));
        tasks.add(new Task("Task2", LocalDate.of(2026, 2, 1), Duration.ofHours(6), nowak, project));

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        Assert.assertEquals(Duration.ofHours(4), report.employees.get(kowalski));
        Assert.assertEquals(Duration.ofHours(6), report.employees.get(nowak));
    }

    // TEST 3 — dwóch pracowników, wiele zadań każdy
    @Test
    public void testTwoEmployeesMultipleTasks() {
        Employee kowalski = new Employee("Kowalski Jan");
        Employee nowak = new Employee("Nowak Piotr");
        Project project = new Project("Project1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 2, 1), Duration.ofHours(2), kowalski, project));
        tasks.add(new Task("Task2", LocalDate.of(2026, 2, 2), Duration.ofHours(3), kowalski, project));
        tasks.add(new Task("Task3", LocalDate.of(2026, 2, 1), Duration.ofHours(5), nowak, project));
        tasks.add(new Task("Task4", LocalDate.of(2026, 2, 3), Duration.ofHours(1), nowak, project));

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        Assert.assertEquals(Duration.ofHours(5), report.employees.get(kowalski));
        Assert.assertEquals(Duration.ofHours(6), report.employees.get(nowak));
    }

    // TEST 4 — pusta lista → pusty raport
    @Test
    public void testEmptyList() {
        List<Task> tasks = new ArrayList<>();

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        Assert.assertTrue(report.employees.isEmpty());
    }

    // TEST 5 — sprawdź że raport zawiera TYLKO pracowników z listy
    @Test
    public void testReportContainsExactlyTheRightEmployees() {
        Employee kowalski = new Employee("Kowalski Jan");
        Project project = new Project("Project1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 2, 1), Duration.ofHours(8), kowalski, project));

        EmployeeReport report = EmployeeReport.generateReport(tasks);

        Assert.assertEquals(1, report.employees.size());
    }

}
