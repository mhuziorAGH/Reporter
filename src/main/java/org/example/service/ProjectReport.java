package org.example.service;

import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectReport {


    public Map<Project, Duration> projects = new HashMap<>();

    public static ProjectReport generateReport(List<Task> tasks) {

        ProjectReport report = new ProjectReport();

        for (Task task : tasks) {
            Project project = task.getProject();
            Duration time = task.getWorkingTime();

            if (!report.projects.containsKey(project)) {
                report.projects.put(project, time);
            } else {
                Duration existing = report.projects.get(project);
                report.projects.put(project, existing.plus(time));
            }
        }

        return report;
    }
}
