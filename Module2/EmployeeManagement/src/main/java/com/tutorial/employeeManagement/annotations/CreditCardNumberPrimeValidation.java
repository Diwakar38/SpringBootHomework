package com.tutorial.employeeManagement.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigInteger;

public class CreditCardNumberPrimeValidation implements ConstraintValidator<CreditCardNumberPrimeValidator, BigInteger> {
    @Override
    public void initialize(CreditCardNumberPrimeValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BigInteger value, ConstraintValidatorContext context) {
        if(value.toString().length() != 12) return false;
        return value.isProbablePrime(100);
    }
}
