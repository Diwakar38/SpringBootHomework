package com.tutorial.prod_ready_features.clients.impl;

import com.tutorial.prod_ready_features.advices.ApiResponse;
import com.tutorial.prod_ready_features.clients.EmployeeClient;
import com.tutorial.prod_ready_features.dtos.EmployeeDto;
import com.tutorial.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {
    private final Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);
    private final RestClient restClient;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.trace("Trying to get all teh employees");
        try {
            ApiResponse<List<EmployeeDto>> employeeDtoList = restClient.get()
                    .uri("/employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) ->{
                        log.error("Error Occured: {}", new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved all the employees!");
            log.trace("Retrieved employee list: {}" ,employeeDtoList.getData());
            return employeeDtoList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeesById(Long id) {
        try {
            Long empId = Long.valueOf(id);
            ApiResponse<EmployeeDto> employeeDtoList = restClient.get()
                    .uri("/employees/{empId}",empId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDto>>() {
                    });
            return employeeDtoList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee) {
        try{
            ApiResponse<EmployeeDto> employeeDto = restClient.post()
                    .uri("/employees")
                    .body(inputEmployee)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) ->{
                        System.out.println("Error Occured: " + new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDto.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
