package com.homework.CollegeManagementSystem.repositories;

import com.homework.CollegeManagementSystem.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
