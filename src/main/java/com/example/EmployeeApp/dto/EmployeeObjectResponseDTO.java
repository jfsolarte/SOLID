package com.example.EmployeeApp.dto;

import com.example.EmployeeApp.model.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeObjectResponseDTO {
    private String status;
    private Employee data;
    private String message;
}
