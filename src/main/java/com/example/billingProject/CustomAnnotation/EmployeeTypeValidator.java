package com.example.billingProject.CustomAnnotation;

import com.example.billingProject.Constants.TypeValidationMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeTypeValidator implements ConstraintValidator<ValidationEmployeeType,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<String>employees= Arrays.asList(TypeValidationMessages.EMPLOYEECUSTOMER,TypeValidationMessages.EMPLOYEESTAFF,TypeValidationMessages.ADMIN);
        return employees.contains(value);
    }
}
