package org.example.output;

import org.example.service.TaskReport;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskReportPrinter {

    public static void printReportTask(TaskReport taskReport) {
        System.out.println("=== RAPORT 4 — Task ranking ===");

              List<Map.Entry<String, Duration>> ranking =
                new ArrayList<>(taskReport.taskTimes.entrySet());

        ranking.sort((a, b) -> b.getValue().compareTo(a.getValue()));

          int position = 1;
        for (Map.Entry<String, Duration> entry : ranking) {
            String taskName = entry.getKey();
            long minutes = entry.getValue().toMinutes();
            long hours = minutes / 60;

            System.out.printf("%d. %-20s %5dh%n", position, taskName, hours);
            position++;
        }
    }
}