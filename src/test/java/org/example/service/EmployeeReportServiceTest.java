package org.example.service;

import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReportServiceTest {

    public void testProperEmployeeReportService() {
        Employee employee = new Employee("Kowalski Jan");
        Project project = new Project("Project1");
        Task task1 = new Task("Task1", LocalDate.of(2026,02,01), Duration.ofHours(1),employee, project);
        Task task2 = new Task("Task2", LocalDate.of(2026,02,01), Duration.ofHours(2),employee, project);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
    }
}
