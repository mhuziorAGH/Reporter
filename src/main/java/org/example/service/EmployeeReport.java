package org.example.service;

import org.example.domain.Employee;
import org.example.domain.Task;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeReport {

    public Map<Employee, Duration> employees = new HashMap<>();

    public static EmployeeReport generateReport(List<Task> tasks) {
//flow -> data(lista) -> trafia do cli (reportgin command) -> lista przesynaa jest do mnie jako argument
        EmployeeReport report = new EmployeeReport();

        for (Task task : tasks) {
            Employee employee = task.getEmployee();
            Duration time = task.getWorkingTime();

            if (!report.employees.containsKey(employee)) {
                report.employees.put(employee, time);
            } else {
                Duration existing = report.employees.get(employee);
                report.employees.put(employee, existing.plus(time));
            }
        }

        return report;
    }
}