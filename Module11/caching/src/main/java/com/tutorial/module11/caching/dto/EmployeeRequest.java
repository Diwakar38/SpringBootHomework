package com.tutorial.module11.caching.dto;

import java.io.Serializable;

public record EmployeeRequest(
        String name,
        String role,
        Integer salary
) implements Serializable {
}
