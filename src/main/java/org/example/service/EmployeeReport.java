package org.example.service;

import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmployeeReport {

    public Map<Employee, Map<Project, Duration>> employeeProjectHours = new HashMap<>();

    public static EmployeeReport generateReport(List<Task> tasks) {
//flow -> data(lista) -> trafia do cli (reportgin command) -> lista przesynaa jest do mnie jako argument

        EmployeeReport report = new EmployeeReport();

        for (Task task : tasks) {
            Employee employee = task.getEmployee();
            Project project = task.getProject();
            Duration time = task.getWorkingTime();

            Map<Project, Duration> projectHours =
                    report.employeeProjectHours.getOrDefault(employee, new HashMap<>());

            Duration existing = projectHours.getOrDefault(project, Duration.ZERO);
            projectHours.put(project, existing.plus(time));

            report.employeeProjectHours.put(employee, projectHours);
        }

        return report;
    }

}