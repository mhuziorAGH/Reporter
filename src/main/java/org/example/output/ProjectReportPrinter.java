package org.example.output;

import org.example.domain.Project;
import org.example.service.ProjectReport;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;

public class ProjectReportPrinter {

    public static void printReportProject(ProjectReport projectReport) {
        System.out.println("=== RAPORT 2 — Projects ===");


        long totalMinutes = 0;
        for (Duration duration : projectReport.projects.values()) {
            totalMinutes += duration.toMinutes();
        }

        for (Map.Entry<Project, Duration> entry : projectReport.projects.entrySet()) {
            Project project = entry.getKey();
            String name = project.getProjectName();
            long projectMinutes = entry.getValue().toMinutes();
            long hours = projectMinutes / 60;
            double percent = (projectMinutes * 100.0) / totalMinutes;

            LocalDate start = projectReport.startDates.get(project);
            LocalDate end = projectReport.endDates.get(project);

            System.out.printf("%-20s %5dh (%.1f%%)  [%s → %s]%n",
                    name, hours, percent, start, end);
        }
    }
}