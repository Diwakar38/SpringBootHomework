package com.tutorial.employeeManagement.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {CreditCardNumberPrimeValidation.class})
public @interface CreditCardNumberPrimeValidator {
    String message() default "Credit card number has to be a prime number of 12 length";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
