package org.example.service;

import org.example.domain.Employee;
import org.example.domain.Task;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeReport {

    Map<Employee, Duration> employees = new HashMap<>();


    public static EmployeeReport generateReport(List<Task> arraylist) {
        EmployeeReport report = new EmployeeReport();

        for (Task task : arraylist) {
            Employee employee = task.getEmployee();
            Duration time = task.getWorkingTime();
            if (!report.employees.containsKey(employee)) {
                report.employees.put(employee, time);
            } else {
                Duration duration = report.employees.get(task.getEmployee());
                Duration newDuration = duration.plus(time);
                report.employees.put(task.getEmployee(), newDuration);
            }


        } return report;
    }

}
