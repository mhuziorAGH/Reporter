package org.example;

import org.example.cli.CLI;

public class App {
    public static void main(String[] args) {
//        CLI cli = new CLI(args);
//        cli.run(args);

        String [] mockArgs = new String[] {"--r", "R2"};
        CLI cli2 = new CLI(mockArgs);
        cli2.run(mockArgs);

    }
}