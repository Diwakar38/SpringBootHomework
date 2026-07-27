package com.tutorial.module11.caching.mapper;

import com.tutorial.module11.caching.dto.EmployeeRequest;
import com.tutorial.module11.caching.dto.EmployeeResponse;
import com.tutorial.module11.caching.entitiy.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    Employee toEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse toEmployeeResponse(Employee employee);
}
