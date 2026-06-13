package com.example.HospitalManagementSystem.dtos;

import com.example.HospitalManagementSystem.entities.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {
    private final BloodGroupType bloodGroupType;
    private final Long count;
}
