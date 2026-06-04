package com.tutorial.employeeManagement.repositories;

import com.tutorial.employeeManagement.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
