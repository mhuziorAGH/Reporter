package org.example.cli;

import org.example.data.ExcelFileFinder;
import org.example.data.FileScanner;
import org.example.domain.Task;
import org.example.output.EmployeeReportPrinter;
import org.example.service.EmployeeReport;

import java.util.List;

public class ReportEmployeesCommand {
    public static void execute(ParamsSet params) {
//        System.out.println("ReportEmployeesCommand");

        //TODO after changes in other packages

        ExcelFileFinder excelFileFinder = new ExcelFileFinder();
        List<Task> tasks = excelFileFinder.run(params);

        new EmployeeReport();
        EmployeeReport employeeReport = EmployeeReport.generateReport(tasks);

        EmployeeReportPrinter.printReport(employeeReport);

    }
}
