package org.example;

import org.example.cli.CLI;
import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;
import org.example.output.EmployeeReportPrinter;
import org.example.output.ProjectReportPrinter;
import org.example.output.TaskReportPrinter;
import org.example.service.EmployeeReport;
import org.example.service.ProjectReport;
import org.example.service.TaskReport;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        CLI cli = new CLI(args);
//        cli.run();

//        testing with params
//        String [] mockArgs = new String[] {"--path", "Resources/reporter-dane/2012/01/Kowalski_Jan.xls", "--r", "R1", "--from", "2012-01-01", "--to", "2012-03-31;",
//                "--path", "Resources/reporter-dane", "--r", "R1", "--from", "2012-01-01", "--to", "2012-03-31;",
//                "--path", "Resources/reporter-dane", "--r", "R1", "--from", "2012-01-01", "--to", "2012-01-15;",
//                "--path", "Resources/reporter-dane", "--r", "R4", "--from", "2012-01-01", "--to", "2012-03-31;"};
//        CLI cli2 = new CLI(mockArgs);
//        cli2.run();

        String[] mockArgs = new String[] {"--path", "Resources/reporter-dane/2012/02/Kowalski_Jan.xls", "--r", "R4"};
        CLI cli = new CLI(mockArgs);
        cli.run();


//        String [] mockArgs3 = new String[] {"--r", "R2;","--help", "--path", "/awda", "--r", "R3;", "--r", "R2;" };
//        CLI cli3 = new CLI(mockArgs);
//        cli3.run();

    }
}