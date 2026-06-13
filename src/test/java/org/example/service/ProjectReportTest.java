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

public class ProjectReportTest {

    // jeden projekt, jedno zadanie
    @Test
    public void testSingleProjectSingleTask() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Projekt1");
        Task task = new Task("Task1", LocalDate.of(2026, 1, 1), Duration.ofHours(8), employee, project);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        ProjectReport report = ProjectReport.generateReport(tasks);

        Assert.assertTrue(report.projects.containsKey(project));
        Assert.assertEquals(Duration.ofHours(8), report.projects.get(project));
    }

    // jeden projekt, wiele zadań
    @Test
    public void testSingleProjectMultipleTasks() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Projekt1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 1, 1), Duration.ofHours(4), employee, project));
        tasks.add(new Task("Task2", LocalDate.of(2026, 1, 2), Duration.ofHours(6), employee, project));

        ProjectReport report = ProjectReport.generateReport(tasks);

        Assert.assertEquals(Duration.ofHours(10), report.projects.get(project));
    }

    //  dwa projekty, osobne taski
    @Test
    public void testTwoProjects() {
        Employee employee = new Employee("Kowalski Jan");
        Project projekt1 = new Project("Projekt1");
        Project projekt2 = new Project("Projekt2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 1, 1), Duration.ofHours(3), employee, projekt1));
        tasks.add(new Task("Task2", LocalDate.of(2026, 1, 1), Duration.ofHours(5), employee, projekt2));

        ProjectReport report = ProjectReport.generateReport(tasks);

        Assert.assertEquals(Duration.ofHours(3), report.projects.get(projekt1));
        Assert.assertEquals(Duration.ofHours(5), report.projects.get(projekt2));
    }

    // dwa projekty, wielu pracowników, wiele zadań
    @Test
    public void testTwoProjectsMultipleEmployees() {
        Employee kowalski = new Employee("Kowalski Jan");
        Employee nowak = new Employee("Nowak Piotr");
        Project projekt1 = new Project("Projekt1");
        Project projekt2 = new Project("Projekt2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 1, 1), Duration.ofHours(4), kowalski, projekt1));
        tasks.add(new Task("Task2", LocalDate.of(2026, 1, 2), Duration.ofHours(2), nowak, projekt1));
        tasks.add(new Task("Task3", LocalDate.of(2026, 1, 1), Duration.ofHours(6), kowalski, projekt2));

        ProjectReport report = ProjectReport.generateReport(tasks);

        Assert.assertEquals(Duration.ofHours(6), report.projects.get(projekt1));
        Assert.assertEquals(Duration.ofHours(6), report.projects.get(projekt2));
    }

    // pusta lista → pusty raport
    @Test
    public void testEmptyList() {
        List<Task> tasks = new ArrayList<>();

        ProjectReport report = ProjectReport.generateReport(tasks);

        Assert.assertTrue(report.projects.isEmpty());
    }

    // check- raport zawiera dokładnie tyle projektów ile w danych
    @Test
    public void testReportSize() {
        Employee employee = new Employee("Kowalski Jan");
        Project projekt1 = new Project("Projekt1");
        Project projekt2 = new Project("Projekt2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", LocalDate.of(2026, 1, 1), Duration.ofHours(4), employee, projekt1));
        tasks.add(new Task("Task2", LocalDate.of(2026, 1, 1), Duration.ofHours(3), employee, projekt2));

        ProjectReport report = ProjectReport.generateReport(tasks);

        Assert.assertEquals(2, report.projects.size());
    }
}