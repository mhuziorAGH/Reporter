package org.example;

import org.example.cli.CLI;
import org.example.data.FileScanner;
import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.domain.Task;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        CLI cli = new CLI(args);

        FileScanner fileScanner = new FileScanner();
        List<List<Object>> list = fileScanner.readExcelFile("/var/home/student/IdeaProjects/Reporter/Resources/reporter-dane/2012/01/Kowalski_Jan.xls");

        for (List<Object> o : list) {
            String employee = (String) o.get(0);
            Employee test1 = new Employee(employee);
            LocalDate date = (LocalDate) o.get(1);
            String task = (String) o.get(2);
            Duration time = (Duration) o.get(3);
            Project project = new Project("Project 1");
            Task test2 = new Task(task, date, time, test1, project);

            List<Task> test = new ArrayList<>();
            test.add(test2);
        }


    }
}