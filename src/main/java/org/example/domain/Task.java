package org.example.domain;

import java.time.Duration;
import java.time.LocalDate;

public class Task {
    private String description;
    private LocalDate startDate;
    private Duration workingTime;

    public Task(String description, LocalDate startDate, Duration workingTime) {
        this.description = description;
        this.startDate = startDate;
        this.workingTime = workingTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public double getWorkingTime() {
        return workingTime;
    }
}
