package com.example.billingProject.CustomAnnotation;

import com.example.billingProject.Service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductIdValidator implements ConstraintValidator<ValidateProductId,Integer> {
    @Autowired
    ProductService productService;
    @Override
    public boolean isValid(Integer productId, ConstraintValidatorContext constraintValidatorContext) {

        if(productService.existsByProductId(productId)){
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate("Product ID " + productId + " is not present")
                .addConstraintViolation();

        return false;
    }
}
