package org.example.apijazbookshop.validation;

import jakarta.validation.ConstraintValidator;

import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;


public class DictionaryValidator implements ConstraintValidator<ValidateBookType, String> {


    @Override
    public boolean isValid(String bookType, ConstraintValidatorContext constraintValidatorContext) {
        List<String> validBookTypes = Arrays.asList("Sci-fun", "Romance");
        return validBookTypes.contains(bookType);
    }
}
