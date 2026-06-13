package org.example.cli;

import java.util.ArrayList;
import java.util.List;

public class CLI {
    private final String[] args;
    private List<ParamsSet> paramsSets = new ArrayList<>();

    public CLI(String[] args) {
        this.args = args;
        this.paramsSets = getParamsSets(args);
    }

    private List<ParamsSet> getParamsSets(String[] args) {
        ArrayList<ParamsSet> paramsSets = new ArrayList<>();
        ParamsSet actualPsSet = new ParamsSet();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            String nextArg = args[i + 1];
            if (arg.startsWith("--") && !nextArg.startsWith("--")) {
                String mode = arg.startsWith("--") ? arg.substring(2) : arg;
                switch (mode) {
                    case "--path" -> {actualPsSet.setPath(nextArg);}
                    case "--from" -> {actualPsSet.setFrom(nextArg);}
                    case "--to" -> {actualPsSet.setTo(nextArg);}
                    case "--r" -> {actualPsSet.setWhichReport(nextArg);}
                    case "--out" -> {actualPsSet.setWhichOutput(nextArg);}
                    default ->  {}
                }
            }
            if (arg.endsWith(";")) {
                paramsSets.add(actualPsSet);
                actualPsSet.clear();
            }
        }

        return paramsSets;
    }

    private void selectMethod(ParamsSet params) {
        String method = params.getWhichReport();

        for (ParamsSet paramsSet : paramsSets) {
            switch (paramsSet.getWhichReport()) {
                case "R1" -> {
                    //TODO  (prams)
                }
                case "R2" -> {
                    //TODO  (prams)
                }
                case "R3" -> {
                    //TODO  (prams)
                }
                case "R4" -> {
                    //TODO  (prams)
                }
                default -> {
                    //TODO  (prams)
                }
            }

        }

    }
}
