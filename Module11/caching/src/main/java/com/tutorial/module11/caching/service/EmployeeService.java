package com.tutorial.module11.caching.service;

import com.tutorial.module11.caching.dto.EmployeeRequest;
import com.tutorial.module11.caching.dto.EmployeeResponse;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    void deleteEmployee(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);
}
