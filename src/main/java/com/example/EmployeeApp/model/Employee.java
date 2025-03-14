package com.example.EmployeeApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;
}
