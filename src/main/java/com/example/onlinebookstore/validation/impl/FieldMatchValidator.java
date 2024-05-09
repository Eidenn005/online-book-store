package com.example.onlinebookstore.validation.impl;

import com.example.onlinebookstore.validation.FieldMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            var fieldValue = value.getClass().getDeclaredField(field);
            var fieldMatchValue = value.getClass().getDeclaredField(fieldMatch);
            fieldValue.setAccessible(true);
            fieldMatchValue.setAccessible(true);

            Object fieldVal = fieldValue.get(value);
            Object fieldMatchVal = fieldMatchValue.get(value);

            return fieldVal != null && fieldVal.equals(fieldMatchVal);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}
