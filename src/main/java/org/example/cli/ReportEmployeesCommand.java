package org.example.cli;

import org.example.data.FileScanner;
import org.example.domain.Task;
import org.example.output.EmployeeReportPrinter;
import org.example.service.EmployeeReport;

import java.util.List;

public class ReportEmployeesCommand {
    public static void execute(ParamsSet params) {
        System.out.println("ReportEmployeesCommand");

        //TODO after changes in other packages

        FileScanner scanner = new FileScanner();
        List<Task> tasks = scanner.readExcelFile(params.getPath());

        new EmployeeReport();
        EmployeeReport employeeReport = EmployeeReport.generateReport(tasks);

        EmployeeReportPrinter.printReport(employeeReport);

    }
}
