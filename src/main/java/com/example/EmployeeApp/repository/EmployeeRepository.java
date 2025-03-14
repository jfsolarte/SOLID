package com.example.EmployeeApp.repository;

import com.example.EmployeeApp.dto.EmployeeListResponseDTO;
import com.example.EmployeeApp.dto.EmployeeObjectResponseDTO;
import com.example.EmployeeApp.exception.EntityNotFoundException;
import com.example.EmployeeApp.exception.ServiceException;
import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.service.MessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final RestTemplate restTemplate;
    private final MessageService messageService;

    private final String BASE_URL = "http://dummy.restapiexample.com/api/v1";

    public EmployeeRepository(RestTemplate restTemplate, MessageService messageService) {
        this.restTemplate = restTemplate;
        this.messageService = messageService;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String url = BASE_URL + "/employees";

        try {

            ResponseEntity<EmployeeListResponseDTO> response = restTemplate.exchange(
                    url, HttpMethod.GET, createHttpEntity(), EmployeeListResponseDTO.class
            );

            List<Employee> employees = response.getBody().getData();

            return employees;

        } catch (Exception e) {
            throw new ServiceException("Error al obtener la lista de empleados.", e);
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        String url = BASE_URL + "/employee/" + id;

        try {
            ResponseEntity<EmployeeObjectResponseDTO> response = restTemplate.exchange(
                    url, HttpMethod.GET, createHttpEntity(), EmployeeObjectResponseDTO.class
            );

            Employee employee = response.getBody().getData();

            if (employee == null) {
                throw new EntityNotFoundException(messageService.getMessage("employee.not.found", id));
            }

            return employee;

        } catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException(messageService.getMessage("employee.not.found", id), e);
        } catch (Exception e) {
            throw new ServiceException(messageService.getMessage("service.unexpected.error", id), e);
        }
    }

    /**
     * Crea una HttpEntity con headers personalizados para evitar bloqueos de la API.
     */
    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        headers.set("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.set("Referer", "http://dummy.restapiexample.com/");
        headers.set("X-Requested-With", "XMLHttpRequest");
        headers.set("Cookie", "humans_21909=1"); // Agregar la cookie manualmente
        return new HttpEntity<>(headers);
    }
}
