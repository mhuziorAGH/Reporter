package org.example.service;

import org.example.data.FileScanner;
import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeReport {

    Map<Employee, Duration> employees = new HashMap<>();

  //  FileScanner fileScanner = new FileScanner();
//    List <List<Object>> list = fileScanner.readExcelFile("/var/home/student/IdeaProjects/Reporter/Resources/reporter-dane/2012/01/Kowalski_Jan.xls");
//
//        for (List<Object> o : list) {
//        String employee = (String) o.get(0);
//        Employee test1 = new Employee(employee);
//        LocalDate date = (LocalDate) o.get(1);
//        String task = (String) o.get(2);
//        Duration time = (Duration) o.get(3);
//        Project project = new Project("Project 1");
//        Task test2 = new Task(task, date, time,test1, project);
//

    public static EmployeeReport generateReport(List<Task> arraylist) {

        EmployeeReport report = new EmployeeReport();

        for (Task task : arraylist) {
            Employee employee = task.getEmployee();
            Duration time = task.getWorkingTime();
            if (!report.employees.containsKey(employee)) {
                report.employees.put(employee, time);
            } else {
                Duration duration = report.employees.get(task.getEmployee());
                Duration newDuration = duration.plus(time);
                report.employees.put(task.getEmployee(), newDuration);
            }

        } return report;
    }
    }




// for (Task task : arraylist) {
//Employee employee = task.getEmployee();
//Duration time = task.getWorkingTime();
//            if (!report.employees.containsKey(employee)) {
//        report.employees.put(employee, time);
//            } else {
//Duration duration = report.employees.get(task.getEmployee());
//Duration newDuration = duration.plus(time);
//                report.employees.put(task.getEmployee(), newDuration);
//        }