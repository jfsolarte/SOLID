package com.example.EmployeeApp.dto;

import com.example.EmployeeApp.model.Employee;
import lombok.Getter;

@Getter
public class EmployeeDTO {
    private final int id;
    private final String employeeName;
    private final int employeeSalary;
    private final int employeeAge;
    private final String profileImage;
    private final double employeeAnualSalary;

    public EmployeeDTO(Employee employee, double annualSalary) {
        this.id = employee.getId();
        this.employeeName = employee.getEmployee_name();
        this.employeeSalary = employee.getEmployee_salary();
        this.employeeAge = employee.getEmployee_age();
        this.profileImage = employee.getProfile_image();
        this.employeeAnualSalary = annualSalary;
    }
}
