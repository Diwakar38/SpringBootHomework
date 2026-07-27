package com.tutorial.module11.caching.repository;

import com.tutorial.module11.caching.entitiy.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
