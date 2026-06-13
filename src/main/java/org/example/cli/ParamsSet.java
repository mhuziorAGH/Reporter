package org.example.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParamsSet {
    // default values
    private String path = "Resources/reporter-dane";
    //yyyy-MM-dd
    private String from = "2010-01-01";
    private String to;
    private String whichReport = "R1";
    private String whichOutput = "out1";

    public ParamsSet() {
        this.to = nextyearDate();
    }

    public String nextyearDate() {
        LocalDate jutro = LocalDate.now().plusDays(365);
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return jutro.format(formater);
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

    @Override
    public String toString() {
        return "ParamsSet{" +
                "path='" + path + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", whichReport='" + whichReport + '\'' +
                ", whichOutput='" + whichOutput + '\'' +
                '}';
    }
}
