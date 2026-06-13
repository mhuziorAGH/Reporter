package org.example.domain;

import java.time.Duration;
import java.time.LocalDate;

public class Task {
    private String description;
    private LocalDate startDate;
    private Duration workingTime;
    private Employee employee;
    private Project project;

    public Task(String description, LocalDate startDate, Duration workingTime, Employee employee, Project project) {
        this.description = description;
        this.startDate = startDate;
        this.workingTime = workingTime;
        this.employee = employee;
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Duration getWorkingTime() {
        return workingTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }
}
