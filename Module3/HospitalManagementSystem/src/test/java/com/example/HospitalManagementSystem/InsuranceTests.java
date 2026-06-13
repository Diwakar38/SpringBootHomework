package com.example.HospitalManagementSystem;

import com.example.HospitalManagementSystem.entities.Appointment;
import com.example.HospitalManagementSystem.entities.Insurance;
import com.example.HospitalManagementSystem.services.AppointmentService;
import com.example.HospitalManagementSystem.services.InsuranceService;
import com.example.HospitalManagementSystem.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Test
    public void testAssignInsuranceToPatient() {
        Insurance insurance = Insurance.builder()
                .provider("HDFC")
                .createdAt(LocalDateTime.now())
                .policyNumber("12345678")
                .validUntil(LocalDate.of(2027,1,1))
                .build();

        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance, 1L);

        System.out.println(updatedInsurance);

//        patientService.deletePatient(1L);

        var patient = insuranceService.removeInsuranceOfAPatient(1L);
        System.out.println(patient);
    }

    @Test
    public void testCreateANewAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .reason("Anxiety Screening")
                .build();

        var updatedAppointment = appointmentService.createANewAppointment(appointment, 1L, 1L);

        System.out.println(updatedAppointment);

        patientService.deletePatient(1L);
    }

}
