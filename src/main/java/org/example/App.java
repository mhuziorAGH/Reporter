package org.example;

import org.example.cli.CLI;
import org.example.data.FileScanner;
import org.example.domain.Task;

import java.util.List;

public class App {
    public static void main(String[] args) {
        FileScanner fileScanner = new FileScanner();
        List<Task> tasks = fileScanner.readExcelFile("/var/home/student/Documents/Reporter/Resources/reporter-dane/2012/02/Tomasz_W.xlsx");
        System.out.println(tasks);
        System.out.println(tasks.size());
        System.out.println(tasks.get(0).getWorkingTime());

        System.out.println(tasks);
        System.out.println(tasks.size());
        System.out.println(tasks.get(1).getWorkingTime());
        System.out.println(tasks);
        System.out.println(tasks.size());
        System.out.println(tasks.get(2).getWorkingTime());
        System.out.println(tasks);
        System.out.println(tasks.size());
        System.out.println(tasks.get(3).getWorkingTime());
        System.out.println(tasks.get(3).getDescription());
    }
}
