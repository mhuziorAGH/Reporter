package org.example.cli;

public class ParamsSet {
    private String path = "";
    private String from = "";
    private String to = "";
    private String whichRaport = "";
    private String whichOutput = "";

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

    public String getWhichRaport() {
        return whichRaport;
    }

    public void setWhichRaport(String whichRaport) {
        this.whichRaport = whichRaport;
    }

    public String getWhichOutput() {
        return whichOutput;
    }

    public void setWhichOutput(String whichOutput) {
        this.whichOutput = whichOutput;
    }
}
