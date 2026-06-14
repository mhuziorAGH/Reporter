package org.example.cli;

import org.example.data.ExcelFileFinder;
import org.example.domain.Task;
import org.example.output.ProjectReportPrinter;
import org.example.output.TaskReportPrinter;
import org.example.service.ProjectReport;
import org.example.service.TaskReport;

import java.util.List;

public class ReportProjectsCommand {
    public static void execute(ParamsSet params) {

        ExcelFileFinder excelFileFinder = new ExcelFileFinder();
        List<Task> tasks = excelFileFinder.run(params);

        new ProjectReport();
       ProjectReport projectReport = ProjectReport.generateReport(tasks);

       ProjectReportPrinter.printReportProject(projectReport);

    }
}
