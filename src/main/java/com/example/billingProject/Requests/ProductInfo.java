package com.example.billingProject.Requests;


import com.example.billingProject.CustomAnnotation.ValidationProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductInfo {

    @NotBlank(message = "Product name should be entered")
    private String productName;
    @NotNull(message ="cost should be entered" )
    private double  cost;
    @NotBlank(message = "Description has to be entered")
    private String description;
    @NotNull(message = "Product type has to be entered")
    @ValidationProductType(message = "INVALID PRODUCT TYPE: It should be either fruits or vegetables")
    private String productType;
    @NotNull(message = "Quantity of product has to be entered")
    private int quantity;

}


