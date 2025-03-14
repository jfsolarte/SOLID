package com.example.EmployeeApp.service;

import com.example.EmployeeApp.dto.EmployeeDTO;
import com.example.EmployeeApp.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(int id);
    double computeAnnualSalary(Employee employee);
}