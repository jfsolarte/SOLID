package com.example.EmployeeApp.service;

import com.example.EmployeeApp.exception.EntityNotFoundException;
import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.dto.EmployeeDTO;
import com.example.EmployeeApp.repository.IEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1);
        employee.setEmployee_name("Jairo Solarte");
        employee.setProfile_image("Software Engineer");
        employee.setEmployee_salary(5000);
        employee.setEmployee_age(38);

        employeeDTO = new EmployeeDTO(employee,employeeService.computeAnnualSalary(employee));
    }
    @Test
    void testGetEmployeeById_Success() {


        Mockito.when(employeeRepository.getEmployeeById(1)).thenReturn(employee);


        EmployeeDTO result = employeeService.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("Jairo Solarte", result.getEmployeeName());
        Mockito.verify(employeeRepository, times(1)).getEmployeeById(1);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        // Arrange: Simulate employee not found
        int employeeId = 2;
        String expectedMessage = "Empleado con ID " + employeeId + " no encontrado.";

        Mockito.doThrow(new EntityNotFoundException(expectedMessage)).when(employeeRepository).getEmployeeById(employeeId);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            employeeService.getEmployeeById(2);
        });

        assertEquals(expectedMessage, exception.getMessage());


    }
}
