package com.tutorial.employeeManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tutorial.employeeManagement.annotations.CreditCardNumberPrimeValidator;
import com.tutorial.employeeManagement.annotations.EmployeeRoleValidation;
import com.tutorial.employeeManagement.annotations.PasswordValidator;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role; //ADMIN, USER

    private Double salary;

    private LocalDate dateOfJoining;

    private Boolean isActive;

    private BigInteger creditCardNumber;

    private String password;
}
