package com.tutorial.module11.caching.service.impl;

import com.tutorial.module11.caching.dto.EmployeeRequest;
import com.tutorial.module11.caching.dto.EmployeeResponse;
import com.tutorial.module11.caching.entitiy.Employee;
import com.tutorial.module11.caching.exception.ResourceNotFoundException;
import com.tutorial.module11.caching.mapper.EmployeeMapper;
import com.tutorial.module11.caching.repository.EmployeeRepository;
import com.tutorial.module11.caching.service.EmployeeService;
import com.tutorial.module11.caching.service.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final String CACHE_NAME = "employees";
    private final SalaryAccountService salaryAccountService;

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<EmployeeResponse> getAllEmployees() {
        log.info("Getting all employee data");
        return employeeRepository.findAll()
                .stream()
                .map(employee -> employeeMapper.toEmployeeResponse(employee))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#id")
    public EmployeeResponse getEmployeeById(Long id) {
        return employeeMapper.toEmployeeResponse(employeeRepository.findById(id)
                             .orElseThrow(()-> new ResourceNotFoundException("Id not found :" + id)));
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME, key = "#result.id")
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.save(employeeMapper.toEmployee(employeeRequest));
        log.info("Saved employee: " + employee.toString());
        salaryAccountService.createAccount(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, key = "#id")
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME, key = "#result.id")
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employeeFromDb = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found: " + id)
        );
        employeeFromDb.setName(employeeRequest.name());
        employeeFromDb.setRole(employeeRequest.role());
        employeeFromDb.setSalary(employeeRequest.salary());
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employeeFromDb));
    }


}
