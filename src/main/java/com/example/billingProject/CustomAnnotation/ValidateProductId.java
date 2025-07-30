package com.example.billingProject.CustomAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductIdValidator.class)
@Documented
public @interface ValidateProductId {
    public String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
