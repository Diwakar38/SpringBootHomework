package com.tutorial.SecurityApp.clients;

import com.tutorial.SecurityApp.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeesById(Long id);
    EmployeeDto createNewEmployee(EmployeeDto inputEmployee);
}
