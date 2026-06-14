package org.example.cli;

import org.example.data.ExcelFileFinder;
import org.example.domain.Task;
import org.example.output.TaskReportPrinter;
import org.example.service.TaskReport;

import java.util.List;

public class ReportTaskRankingCommand {
    public static void execute(ParamsSet params) {
        //TODO after changes in other packages

        ExcelFileFinder excelFileFinder = new ExcelFileFinder();
        List<Task> tasks = excelFileFinder.run(params);

        new TaskReport();
        TaskReport taskReport = TaskReport.generateReport(tasks);

        TaskReportPrinter.printReportTask(taskReport);

    }
}
