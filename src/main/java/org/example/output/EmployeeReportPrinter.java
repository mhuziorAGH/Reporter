package org.example.output;

import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.service.EmployeeReport;

import java.time.Duration;
import java.util.Map;

public class EmployeeReportPrinter {

    public static void printReport(EmployeeReport employeeReport) {


        System.out.println("=== RAPORT 1 — Employee hours ===");

        Employee topEmployee = null;
        long maxMinutes = -1;
        for (Map.Entry<Employee, Map<Project, Duration>> empEntry
                : employeeReport.employeeProjectHours.entrySet()) {
            long total = 0;
            for (Duration duration : empEntry.getValue().values()) {
                total += duration.toMinutes();
            }
            if (total > maxMinutes) {
                maxMinutes = total;
                topEmployee = empEntry.getKey();
            }
        }

        for (Map.Entry<Employee, Map<Project, Duration>> empEntry
                : employeeReport.employeeProjectHours.entrySet()) {

            Employee employee = empEntry.getKey();
            String employeeName = employee.getName();
            Map<Project, Duration> projects = empEntry.getValue();

            long totalMinutes = 0;
            for (Duration duration : projects.values()) {
                totalMinutes += duration.toMinutes();
            }

            String marker = employee.equals(topEmployee) ? "  ⭐ Tytan pracy" : "";
            Duration total = Duration.ZERO;
            for (Duration duration : projects.values()) {
                total = total.plus(duration);
            }
            System.out.println(employeeName + " (" + total.toHours() + "h " + total.toMinutesPart() + "m):" + marker);


            for (Map.Entry<Project, Duration> projEntry : projects.entrySet()) {
                String projectName = projEntry.getKey().getProjectName();
                Duration projDuration = projEntry.getValue();

                double percent = (projDuration.toMinutes() * 100.0) / total.toMinutes();

                System.out.printf("  %s: %dh %dm (%.1f%%)%n",
                        projectName,
                        projDuration.toHours(),
                        projDuration.toMinutesPart(),
                        percent);
                System.out.println();
            }
        }
    }
}