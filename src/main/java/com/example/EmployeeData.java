package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class EmployeeData {
    @JsonProperty("employees")
    private List<Employee> employees;

    // Default constructor (required by Jackson)
    public EmployeeData() {
    }

    // Constructor with parameters
    public EmployeeData(List<Employee> employees) {
        this.employees = employees;
    }

    // Getters and Setters
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}


