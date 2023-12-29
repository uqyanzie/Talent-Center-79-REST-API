package com.tujuhsembilan.app.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.app.dto.auth.UserRegisterRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class ValidationService {
    

    @Autowired
    private Validator validator;

    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if(!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
