package com.example.EmployeeApp.dto;

import com.example.EmployeeApp.model.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListResponseDTO {
    private String status;
    private List<Employee> data;
    private String message;
}
