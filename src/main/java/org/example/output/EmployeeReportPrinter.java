package org.example.output;

import org.example.domain.Employee;
import org.example.service.EmployeeReport;

import java.time.Duration;
import java.util.Map;

public class EmployeeReportPrinter {

    public static void printReport(EmployeeReport employeeReport) {
        System.out.println("=== RAPORT 1 — GODZINY PRACOWNIKÓW ===");

        for (Map.Entry<Employee, Duration> entry : employeeReport.employees.entrySet()) {
            String name = entry.getKey().getName();
            long hours = entry.getValue().toHours();
            System.out.println(name + " : " + hours + "h");
        }
    }
}
