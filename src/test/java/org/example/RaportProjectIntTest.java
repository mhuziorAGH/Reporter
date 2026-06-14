package org.example;

import org.example.data.FileScanner;
import org.example.domain.Project;
import org.example.domain.Task;
import org.example.service.EmployeeReport;
import org.example.service.ProjectReport;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.*;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RaportProjectIntTest {

    @Test
    void happyPath_projectReport_readAllFilesFromFolders() throws Exception {

        URL rootUrl = getClass().getClassLoader().getResource("reported-dane/2012/01");
        assertNotNull(rootUrl, "Folder/ file not found!");

        Path root = Paths.get(rootUrl.toURI());
        FileScanner scanner = new FileScanner();

        List<Path> excelFiles = Files.walk(root)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".xls") || p.toString().endsWith(".xlsx"))
                .toList();

        assertFalse(excelFiles.isEmpty(), "Not found excel file!");


        List<Task> allTasks = new ArrayList<>();
        for (Path p : excelFiles) {
            try {
                allTasks.addAll(scanner.readExcelFile(p.toString()));
            } catch (Exception ignored) {
                // HAPPY PATH: ignoruję błędne pliki lub puste wiersze
            }
        }

        // Taski, które mogłyby wywołać NPE
        List<Task> safeTasks = allTasks.stream()
                .filter(t -> t.getProject() != null)
                .filter(t -> t.getWorkingTime() != null)
                .toList();

        assertFalse(safeTasks.isEmpty(), "Not found safe task!");


        ProjectReport report = ProjectReport.generateReport(safeTasks);


        assertFalse(report.projects.isEmpty(), "Raport is empty!");


        report.projects.forEach((project, duration) -> {
            assertTrue(duration.toMinutes() > 0,
                    "Projekt " + project.getProjectName() + " ma 0 minut!");
        });
    }


}




