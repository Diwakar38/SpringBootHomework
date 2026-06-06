package com.tutorial.employeeManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {

    private Long id;

    @NotBlank
    private String title;

    @AssertTrue
    private boolean isActive;

    @Positive
    private Integer employeeCount;

    @URL
    private String websiteURL;

    @Email
    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate createdAt;

    @Positive
    @Min(value = 10000, message = "Budget should be more than 10000")
    @Max(value = 100000000, message = "Budget shall not be more than 100000000")
    private BigInteger budget;

    private BigInteger revenue;

    @Positive
    private BigInteger expenditure;
}
