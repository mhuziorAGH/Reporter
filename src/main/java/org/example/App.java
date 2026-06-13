package org.example;

import org.example.cli.CLI;

public class App {
    public static void main(String[] args) {
        CLI cli = new CLI(args);
        cli.run();

//        testing with params
        String [] mockArgs = new String[] {"--path", "Resources/reporter-dane/2012/01/Nowak_Piotr.xls", "--r", "R1"};
        CLI cli2 = new CLI(mockArgs);
        cli2.run();



//        String [] mockArgs3 = new String[] {"--r", "R2;","--help", "--path", "/awda", "--r", "R3;", "--r", "R2;" };
//        CLI cli3 = new CLI(mockArgs);
//        cli3.run();

    }
}