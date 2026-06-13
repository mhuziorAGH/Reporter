package org.example.cli;

import java.util.ArrayList;
import java.util.List;

public class CLI {
    private List<ParamsSet> paramsSets = new ArrayList<>();

    public CLI(String[] args) {
        this.paramsSets = getParamsSets(args);
    }

    public void run(String[] args) {
        paramsSets = getParamsSets(args);
        selectMethod();
    }

    private List<ParamsSet> getParamsSets(String[] args) {
        ArrayList<ParamsSet> paramsSets = new ArrayList<>();
        ParamsSet actualPsSet = new ParamsSet();

        if (args.length > 1) {

            //TODO --help
            for (int i = 0; i < args.length - 1; i++) {
                String arg = args[i];
                System.out.println(arg);

                String nextArg = args[i + 1];
                System.out.println(nextArg);

                if (arg.startsWith("--") && !nextArg.startsWith("--")) {
                    String mode = arg.startsWith("--") ? arg.substring(2) : arg;
                    switch (mode) {
                        case "--path" -> {
                            actualPsSet.setPath(nextArg);
                        }
                        case "--from" -> {
                            actualPsSet.setFrom(nextArg);
                        }
                        case "--to" -> {
                            actualPsSet.setTo(nextArg);
                        }
                        case "--r" -> {
                            actualPsSet.setWhichReport(nextArg);
                            System.out.println("is");
                        }
                        case "--out" -> {
                            actualPsSet.setWhichOutput(nextArg);
                        }
                        default -> {
                        }
                    }
                }
                if (arg.endsWith(";")) {
                    paramsSets.add(actualPsSet);
                    actualPsSet.clear();
                }
            }
        } else {
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
                    System.out.println("isSelected");
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
