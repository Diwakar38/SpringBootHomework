package com.tutorial.employeeManagement.service;

import com.tutorial.employeeManagement.dtos.DepartmentDTO;
import com.tutorial.employeeManagement.entities.DepartmentEntity;
import com.tutorial.employeeManagement.exceptions.ResourceNotFoundException;
import com.tutorial.employeeManagement.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO addDepartment(DepartmentDTO newDepartmentDto) {
        return modelMapper.map(departmentRepository.save(
                modelMapper.map(newDepartmentDto, DepartmentEntity.class)), DepartmentDTO.class);
    }

    public Optional<DepartmentDTO> getDepartment(Long id) {
        return departmentRepository.findById(id)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }
}
