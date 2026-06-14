package org.example.service;

import org.example.domain.Task;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskReport {

    public Map<String, Duration> taskTimes = new HashMap<>();

    public static TaskReport generateReport(List<Task> tasks) {

        TaskReport taskReport = new TaskReport();

        for (Task task : tasks) {
            String description = task.getDescription();
            Duration time = task.getWorkingTime();

            Duration existing = taskReport.taskTimes.getOrDefault(description, Duration.ZERO);
            taskReport.taskTimes.put(description, existing.plus(time));
        }

        return taskReport;
    }
}