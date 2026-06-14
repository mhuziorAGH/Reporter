package org.example;

import org.example.cli.CLI;
import org.example.data.FileScanner;
import org.example.domain.Task;

import java.util.List;

public class App {
    public static void main(String[] args) {
        FileScanner fileScanner = new FileScanner();
        List<Task> tasks = fileScanner.readExcelFile("Resources/reporter-dane/2012/02/Bledna_Janina.xlsx");
        System.out.println(tasks);
    }
}
