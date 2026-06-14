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

public class TaskRankReportTest {

    //1 zadanie i czy czastrwania trafia do mapy
    @Test
    public void testSingleTask() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Projekt1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Pisanie kodu", LocalDate.of(2026, 2, 1), Duration.ofHours(3), employee, project));

        TaskReport report = TaskReport.generateReport(tasks);

        assertEquals(Duration.ofHours(3), report.taskTimes.get("Pisanie kodu"));
    }

    // wielokrotnosc zadania + suma godzin
    @Test
    public void testSameTaskAggregates() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Projekt1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Testy", LocalDate.of(2026, 2, 1), Duration.ofHours(2), employee, project));
        tasks.add(new Task("Testy", LocalDate.of(2026, 2, 2), Duration.ofHours(4), employee, project));

        TaskReport report = TaskReport.generateReport(tasks);

        assertEquals(Duration.ofHours(6), report.taskTimes.get("Testy"));
    }

    // różne zadania - różne entries
    @Test
    public void testDifferentTasks() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Projekt1");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Kodowanie", LocalDate.of(2026, 2, 1), Duration.ofHours(5), employee, project));
        tasks.add(new Task("Spotkania", LocalDate.of(2026, 2, 1), Duration.ofHours(1), employee, project));

        TaskReport report = TaskReport.generateReport(tasks);

        assertEquals(Duration.ofHours(5), report.taskTimes.get("Kodowanie"));
        assertEquals(Duration.ofHours(1), report.taskTimes.get("Spotkania"));
       assertEquals(2, report.taskTimes.size());
    }

    // pustalista  = pusta mapa check
    @Test
    public void testEmptyList() {
        List<Task> tasks = new ArrayList<>();

        TaskReport report = TaskReport.generateReport(tasks);

        assertTrue(report.taskTimes.isEmpty());
    }
}