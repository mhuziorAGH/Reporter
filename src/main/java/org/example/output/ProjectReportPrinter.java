package org.example.output;

import org.example.domain.Project;
import org.example.service.ProjectReport;

import java.time.Duration;
import java.util.Map;

public class ProjectReportPrinter {

    public static void printReport(ProjectReport projectReport) {
        System.out.println("=== RAPORT 2 — GODZINY NA PROJEKT ===");

        for (Map.Entry<Project, Duration> entry : projectReport.projects.entrySet()) {
            String name = entry.getKey().getProjectName();
            long hours = entry.getValue().toHours();
            System.out.println(name + " : " + hours + "h");
        }
    }
}