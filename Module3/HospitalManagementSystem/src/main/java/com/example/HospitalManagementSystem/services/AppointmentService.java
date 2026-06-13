package com.example.HospitalManagementSystem.services;

import com.example.HospitalManagementSystem.entities.Appointment;
import com.example.HospitalManagementSystem.entities.Doctor;
import com.example.HospitalManagementSystem.entities.Patient;
import com.example.HospitalManagementSystem.repositories.AppointmentRepository;
import com.example.HospitalManagementSystem.repositories.DoctorRepository;
import com.example.HospitalManagementSystem.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createANewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);
    }
}
