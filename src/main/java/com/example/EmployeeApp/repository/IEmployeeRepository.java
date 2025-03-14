package com.example.EmployeeApp.repository;

import com.example.EmployeeApp.model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
}
