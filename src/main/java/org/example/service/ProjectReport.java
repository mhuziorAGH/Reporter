
package org.example.service;

import org.example.domain.Project;
import org.example.domain.Task;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectReport {

    public Map<Project, Duration> projects = new HashMap<>();
    public Map<Project, LocalDate> startDates = new HashMap<>();   // najwcześniejsza data
    public Map<Project, LocalDate> endDates = new HashMap<>();     // najpóźniejsza data

    public static ProjectReport generateReport(List<Task> tasks) {

        ProjectReport report = new ProjectReport();

        for (Task task : tasks) {
            Project project = task.getProject();
            Duration time = task.getWorkingTime();
            LocalDate date = task.getStartDate();


            if (!report.projects.containsKey(project)) {
                report.projects.put(project, time);
            } else {
                Duration existing = report.projects.get(project);
                report.projects.put(project, existing.plus(time));
            }

            LocalDate currentStart = report.startDates.get(project);
            if (currentStart == null || date.isBefore(currentStart)) {
                report.startDates.put(project, date);
            }

            LocalDate currentEnd = report.endDates.get(project);
            if (currentEnd == null || date.isAfter(currentEnd)) {
                report.endDates.put(project, date);
            }
        }

        return report;
    }
}