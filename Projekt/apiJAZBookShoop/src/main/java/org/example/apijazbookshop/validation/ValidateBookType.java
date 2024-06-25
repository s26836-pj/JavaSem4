package org.example.apijazbookshop.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(validatedBy = DictionaryValidator.class)
    public @interface ValidateBookType {

        String message() default "Invalid book type: It should be either 'Sci-fun' or 'Romance'";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

