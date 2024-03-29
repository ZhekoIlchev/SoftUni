package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.util;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {
    <E> Set<ConstraintViolation<E>> violation(E entity);

    <E> boolean isValid(E entity);
}