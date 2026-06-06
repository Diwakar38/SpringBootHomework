package com.tutorial.employeeManagement.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {PasswordValidation.class})
public @interface PasswordValidator {
    String message() default "Password should have at least: one uppercase, one lowercase, one special character and min 10 length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
