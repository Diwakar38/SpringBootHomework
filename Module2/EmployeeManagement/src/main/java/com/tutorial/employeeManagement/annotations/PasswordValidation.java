package com.tutorial.employeeManagement.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<PasswordValidator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        if (value.length() < 10) return false;
        if (!value.matches(".*[a-z].*")) return false;   // at least one lowercase
        if (!value.matches(".*[A-Z].*")) return false;   // at least one uppercase
        if (!value.matches(".*[^a-zA-Z0-9].*")) return false; // at least one special char
        return true;

    }
}
