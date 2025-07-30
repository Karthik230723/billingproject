package com.example.billingProject.CustomAnnotation;

import com.example.billingProject.Constants.TypeValidationMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ProductTypeValidator implements ConstraintValidator<ValidationProductType,String> {

    @Override
    public boolean isValid(String productType, ConstraintValidatorContext constraintValidatorContext) {
        List<String>productTypes= Arrays.asList(TypeValidationMessages.PRODUCTFRUIT,TypeValidationMessages.PRODUCTVEGETABLE);
        return productTypes.contains(productType);
    }
}
