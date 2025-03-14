package com.example.EmployeeApp.service;


import com.example.EmployeeApp.dto.EmployeeDTO;
import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.getAllEmployees().stream()
                .map(emp -> new EmployeeDTO(emp, computeAnnualSalary(emp)))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        double annualSalary = computeAnnualSalary(employee);
        return new EmployeeDTO(employee, annualSalary);
    }

    @Override
    public double computeAnnualSalary(Employee employee) {
        return employee.getEmployee_salary() * 12;
    }
}
