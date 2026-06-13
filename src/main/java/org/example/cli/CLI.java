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
        //TODO
        return new ArrayList<>();
    }

    private void selectMethod(ParamsSet params) {
        String method = params.getWhichRaport();

        switch (method) {
            case "R1" -> {}
            case "R2" -> {}
            case "R3" -> {}
            case "R4" -> {}
            default -> {}
        }

    }
}
