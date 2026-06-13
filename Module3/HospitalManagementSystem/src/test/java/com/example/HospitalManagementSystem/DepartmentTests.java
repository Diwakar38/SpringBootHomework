package com.example.HospitalManagementSystem;

import com.example.HospitalManagementSystem.repositories.DepartmentRepository;
import com.example.HospitalManagementSystem.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentTests {
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testDeleteDepartment() {
        departmentService.printAllDepartments();
        departmentService.deleteDepartment(1L);
        departmentService.printAllDepartments();
    }
}
