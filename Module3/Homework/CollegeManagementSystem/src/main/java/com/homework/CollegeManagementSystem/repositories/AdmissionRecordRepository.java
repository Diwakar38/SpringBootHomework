package com.homework.CollegeManagementSystem.repositories;

import com.homework.CollegeManagementSystem.entities.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord, Long> {
}
