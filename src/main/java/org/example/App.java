package org.example;

import org.example.cli.CLI;

public class App {
    public static void main(String[] args) {
        CLI cli = new CLI(args);
        cli.run();

//        testing with params
//        String [] mockArgs = new String[] {"--r", "R3"};
//        String [] mockArgs = new String[] {"--r", "R2;","--help", "--path", "/awda", "--r", "R3;", "--r", "R2;" };
//        CLI cli2 = new CLI(mockArgs);
//        cli2.run();

    }
}