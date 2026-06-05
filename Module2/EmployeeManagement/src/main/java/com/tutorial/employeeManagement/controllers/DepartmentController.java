package com.tutorial.employeeManagement.controllers;

import com.tutorial.employeeManagement.dtos.DepartmentDTO;
import com.tutorial.employeeManagement.exceptions.ResourceNotFoundException;
import com.tutorial.employeeManagement.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody DepartmentDTO updatedDepartmentDto, @PathVariable Long id) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(updatedDepartmentDto,id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updatePartialDepartmentById(@RequestBody Map<String, Object> updates,
                                                                     @PathVariable Long id) {
        return ResponseEntity.ok(departmentService.updatePartialDepartmentById(updates, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id) {
        boolean gotDeleted = departmentService.deleteDepartmentById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
