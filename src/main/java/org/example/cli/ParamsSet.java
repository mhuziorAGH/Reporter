package org.example.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParamsSet {
    // default values
    private String defaultPath = "Resources/reporter-dane";
    //yyyy-MM-dd
    private String defaultFrom = "2010-01-01";
    private String defaultTo;
    private String defaultReport = "";
    //TODO default
    private String defaultOutput = "";
    //TODO default

    // working values
    private String path;
    private String from;
    private String to;
    private String whichReport;
    private String whichOutput;


    public ParamsSet() {
        this.path = defaultPath;
        this.defaultTo = nextyearDate();
        this.from = defaultFrom;
        this.to = defaultTo;
        this.whichReport = defaultReport;
        this.whichOutput = defaultOutput;
    }

    public String nextyearDate() {
        LocalDate jutro = LocalDate.now().plusDays(365);
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return jutro.format(formater);
    }

    public void clear() {
        path=defaultPath;
        from=defaultFrom;
        to=defaultTo;
        whichReport=defaultReport;
        whichOutput=defaultOutput;
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
