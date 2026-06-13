package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String projectName;
    private List<Employee> employee = new ArrayList<>();

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }
    public void addEmployeeToList(Employee employee) {
        this.employee.add(employee);
    }

    public List<Employee> getEmployeeList() {
        return employee;
    }
}
