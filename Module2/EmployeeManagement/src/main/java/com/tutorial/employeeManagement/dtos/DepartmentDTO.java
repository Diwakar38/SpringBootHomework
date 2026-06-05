package com.tutorial.employeeManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

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

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate createdAt;
}
