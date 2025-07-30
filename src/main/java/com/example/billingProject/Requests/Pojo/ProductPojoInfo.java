package com.example.billingProject.Requests.Pojo;

import com.example.billingProject.CustomAnnotation.ValidateProductId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductPojoInfo {
    @NotNull(message = "Product Id should entered")
    @ValidateProductId
    private int id;
    @NotNull(message = "Quantity has to be entered")
    private int quantity;
}
