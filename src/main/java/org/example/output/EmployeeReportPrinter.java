package org.example.output;

import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.service.EmployeeReport;

import java.time.Duration;
import java.util.Map;

public class EmployeeReportPrinter {

    public static void printReport(EmployeeReport employeeReport) {

        System.out.println("=== RAPORT 1 — Employee hours ===");


        for (Map.Entry<Employee, Map<Project, Duration>> empEntry
                : employeeReport.employeeProjectHours.entrySet()) {

            String employeeName = empEntry.getKey().getName();
            Map<Project, Duration> projects = empEntry.getValue();

            //  minuty + pracownik (suma wszystkich projektów)
            long totalMinutes = 0;
            for (Duration duration : projects.values()) {
                totalMinutes += duration.toMinutes();
            }

            // nagłówek: pracownik + suma godzin
            System.out.println(employeeName + " (" + (totalMinutes / 60) + "h):");

            //  projekt z godzinami i procentem
            for (Map.Entry<Project, Duration> projEntry : projects.entrySet()) {
                String projectName = projEntry.getKey().getProjectName();
                long projectMinutes = projEntry.getValue().toMinutes();
                long hours = projectMinutes / 60;
                double percent = (projectMinutes * 100.0) / totalMinutes;

                System.out.printf("  %s: %dh (%.1f%%)%n", projectName, hours, percent);
            }
        }
    }
}