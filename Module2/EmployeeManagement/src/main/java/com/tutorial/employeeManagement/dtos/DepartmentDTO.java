package com.tutorial.employeeManagement.dtos;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

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

    @Past
    private LocalDateTime createdAt;
}
