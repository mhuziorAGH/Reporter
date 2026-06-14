
package org.example.output;

import org.example.domain.Project;
import org.example.service.ProjectReport;

import java.time.Duration;
import java.util.Map;

public class ProjectReportPrinter {

    public static void printReportProject(ProjectReport projectReport) {
        System.out.println("=== RAPORT 2 — Projects ===");

        // 1. policz łączny czas wszystkich projektów (mianownik do %)
        long totalMinutes = 0;
        for (Duration duration : projectReport.projects.values()) {
            totalMinutes += duration.toMinutes();
        }

        // 2. wypisz każdy projekt z godzinami i % udziału
        for (Map.Entry<Project, Duration> entry : projectReport.projects.entrySet()) {
            String name = entry.getKey().getProjectName();
            long projectMinutes = entry.getValue().toMinutes();
            long hours = projectMinutes / 60;
            double percent = (projectMinutes * 100.0) / totalMinutes;

            System.out.printf("%-20s %5dh (%.1f%%)%n", name, hours, percent);
        }
    }
}