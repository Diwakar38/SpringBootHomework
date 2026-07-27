package com.tutorial.module11.caching.dto;

import java.io.Serializable;

public record EmployeeResponse(
        Long id,
        String name,
        String role,
        Integer salary
) implements Serializable {
}
