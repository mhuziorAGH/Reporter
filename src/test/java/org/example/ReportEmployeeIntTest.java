package org.example;

import org.example.data.FileScanner;
import org.example.domain.Project;
import org.example.domain.Task;
import org.example.service.EmployeeReport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportEmployeeIntTest {

    private static String testFile;

    @BeforeAll
    public static void findTestFile() throws IOException {
        testFile = Files.walk(Paths.get("Resources/reporter-dane"))
                .filter(p -> p.toString().endsWith(".xls"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Brak pliku .xls w Resources/reporter-dane"))
                .toString();
    }

    @Test
    public void fileLoadsCorrectly() {
        List<Task> tasks = new FileScanner().readExcelFile(testFile);
        assertFalse(tasks.isEmpty());
    }

    @Test
    public void dataIsParsedCorrectly() {
        List<Task> tasks = new FileScanner().readExcelFile(testFile);
        Task first = tasks.get(0);

        assertNotNull(first.getEmployee().getName());
        assertNotNull(first.getProject().getProjectName());
        assertNotNull(first.getStartDate());
        assertTrue(first.getWorkingTime().toMinutes() > 0);
    }

    @Test
    public void totalHoursAreSummedAcrossAllProjects() {
        List<Task> tasks = new FileScanner().readExcelFile(testFile);
        EmployeeReport report = EmployeeReport.generateReport(tasks);

        Map<Project, Duration> projectHours =
                report.employeeProjectHours.values().iterator().next();

        long totalMinutes = projectHours.values().stream()
                .mapToLong(Duration::toMinutes)
                .sum();

        assertTrue(totalMinutes > 0);
    }
}