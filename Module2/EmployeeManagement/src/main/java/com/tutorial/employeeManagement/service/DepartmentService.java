package com.tutorial.employeeManagement.service;

import com.tutorial.employeeManagement.dtos.DepartmentDTO;
import com.tutorial.employeeManagement.entities.DepartmentEntity;
import com.tutorial.employeeManagement.exceptions.ResourceNotFoundException;
import com.tutorial.employeeManagement.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

    public Optional<DepartmentDTO> getDepartment(Long id) {
        return departmentRepository.findById(id)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public DepartmentDTO addDepartment(DepartmentDTO newDepartmentDto) {
        return modelMapper.map(departmentRepository.save(
                modelMapper.map(newDepartmentDto, DepartmentEntity.class)), DepartmentDTO.class);
    }

    public DepartmentDTO updatePartialDepartmentById(Map<String, Object> updates, Long id) {
        isExistByDepartmentId(id);
        DepartmentEntity departmentEntityFromDb = departmentRepository.findById(id).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentEntityFromDb,value);
        } );
        return modelMapper.map(departmentRepository.save(departmentEntityFromDb), DepartmentDTO.class);
    }

    private void isExistByDepartmentId(Long id) {
        boolean isExist = departmentRepository.existsById(id);
        if(!isExist) throw new ResourceNotFoundException("Department with id " + id + " was not found");
    }

    public DepartmentDTO updateDepartmentById(DepartmentDTO updatedDepartmentDto, Long id) {
        isExistByDepartmentId(id);
        DepartmentEntity departmentEntity = modelMapper.map(updatedDepartmentDto, DepartmentEntity.class);
        departmentEntity.setId(id);
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }

    public boolean deleteDepartmentById(Long id) {
        isExistByDepartmentId(id);
        departmentRepository.deleteById(id);
        return true;
    }
}
