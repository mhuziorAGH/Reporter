package org.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(projectName, project.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName);
    }
}
