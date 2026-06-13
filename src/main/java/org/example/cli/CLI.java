package org.example.cli;

import java.util.ArrayList;
import java.util.List;

public class CLI {
    private List<ParamsSet> paramsSets = new ArrayList<>();

    public CLI(String[] args) {
        this.paramsSets = getParamsSets(args);
    }

    public void run() {
        selectMethod();
    }

    private List<ParamsSet> getParamsSets(String[] args) {
        List<ParamsSet> paramsSets = new ArrayList<>();
        ParamsSet actualPsSet = new ParamsSet();

        for (int i = 0; i < args.length - 1; i++) {
            String arg = args[i];
            String nextArg = args[i + 1];

            if (arg.startsWith("--") && !nextArg.startsWith("--")) {
                String mode = arg.substring(2);

                String value = nextArg.endsWith(";")
                        ? nextArg.substring(0, nextArg.length() - 1)
                        : nextArg;

                switch (mode) {
                    case "path" -> actualPsSet.setPath(value);
                    case "from" -> actualPsSet.setFrom(value);
                    case "to" -> actualPsSet.setTo(value);
                    case "r" -> actualPsSet.setWhichReport(value);
                    case "out" -> actualPsSet.setWhichOutput(value);
                    default -> System.out.println("Nieznany parametr: " + arg);
                }

                if (nextArg.endsWith(";")) {
                    paramsSets.add(actualPsSet);
                    actualPsSet = new ParamsSet();
                }
            }
        }

        if (!actualPsSet.getWhichReport().isEmpty()) {
            paramsSets.add(actualPsSet);
        }

        return paramsSets;
    }


    private void selectMethod() {
        for (ParamsSet paramsSet : paramsSets) {
            switch (paramsSet.getWhichReport()) {
                case "R1" -> {
                    ReportEmployeesCommand.execute(paramsSet);
                }
                case "R2" -> {
                    ReportProjectsCommand.execute(paramsSet);
                }
                case "R3" -> {
                    ReportEmployeeProjectsCommand.execute(paramsSet);
                }
                case "R4" -> {
                    ReportTaskRankingCommand.execute(paramsSet);
                }
                default -> {
                    ReportEmployeesCommand.execute(paramsSet);
                }
            }

        }

    }
}
