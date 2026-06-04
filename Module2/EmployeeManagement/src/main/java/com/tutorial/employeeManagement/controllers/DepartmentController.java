package com.tutorial.employeeManagement.controllers;

import com.tutorial.employeeManagement.dtos.DepartmentDTO;
import com.tutorial.employeeManagement.exceptions.ResourceNotFoundException;
import com.tutorial.employeeManagement.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @GetMapping
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartment(id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + id + " was not found"));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO newDepartmentDto) {
        return ResponseEntity.ok(departmentService.addDepartment(newDepartmentDto));
    }


}
