package com.example.HospitalManagementSystem.services;

import com.example.HospitalManagementSystem.entities.Department;
import com.example.HospitalManagementSystem.repositories.DepartmentRepository;
import com.example.HospitalManagementSystem.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow();
        departmentRepository.deleteById(departmentId);
    }

    public void printAllDepartments() {
        System.out.println("DepartmentList: " + departmentRepository.findAll());
        System.out.println("DoctorList: " + doctorRepository.findAll());
    }
}
