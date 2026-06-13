package org.example;

import org.example.cli.CLI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        CLI cli = new CLI(args);

        LocalDate jutro = LocalDate.now().plusDays(365);
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(jutro.format(formater));
    }
}