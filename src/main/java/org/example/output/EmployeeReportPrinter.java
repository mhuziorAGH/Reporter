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

            //  minuty + pracownik (suma wszystkich projektów)
            long totalMinutes = 0;
            for (Duration duration : projects.values()) {
                totalMinutes += duration.toMinutes();
            }

            // nagłówek: pracownik + suma godzin
            String marker = employee.equals(topEmployee) ? "  ⭐ Tytan pracy" : "";
            System.out.println(employeeName + " (" + (totalMinutes / 60) + "h):" + marker);

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