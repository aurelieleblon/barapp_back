package com.barapp.barapp_backend.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrixDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testPrixNull() {
        PrixDto dto = new PrixDto();
        Set<ConstraintViolation<PrixDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Le prix ne doit pas être nul", violations.iterator().next().getMessage());
    }

    @Test
    public void testPrixNegatif() {
        PrixDto dto = new PrixDto(-10.0);
        Set<ConstraintViolation<PrixDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Le prix doit être positif ou nul", violations.iterator().next().getMessage());
    }

    @Test
    public void testPrixValide() {
        PrixDto dto = new PrixDto(15.0);
        Set<ConstraintViolation<PrixDto>> violations = validator.validate(dto);

        assertEquals(0, violations.size());
    }
}

