package com.example.HospitalManagementSystem;

import com.example.HospitalManagementSystem.entities.Patient;
import com.example.HospitalManagementSystem.repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTests {
    @Autowired
    PatientRepository patientRepository;

    @Test
    public void getAllPatientsWithAppointments() {
        List<Patient> patients = patientRepository.getAllPatientsWithAppointment();
        for(Patient p : patients) {
            System.out.println(p);
        }
    }
}
