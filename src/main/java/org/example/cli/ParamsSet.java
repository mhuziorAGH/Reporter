package org.example.cli;

public class ParamsSet {
    // default value
    private String path = "Resources/reporter-dane";
    private String from = "";
    //TODO default
    private String to = "";
    //TODO default
    private String whichReport = "";
    //TODO default
    private String whichOutput = "";
    //TODO default

    public ParamsSet() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getWhichReport() {
        return whichReport;
    }

    public void setWhichReport(String whichReport) {
        this.whichReport = whichReport;
    }

    public String getWhichOutput() {
        return whichOutput;
    }

    public void setWhichOutput(String whichOutput) {
        this.whichOutput = whichOutput;
    }
}
