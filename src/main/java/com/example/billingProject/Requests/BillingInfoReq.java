package com.example.billingProject.Requests;

import com.example.billingProject.CustomAnnotation.ValidateProductId;
import com.example.billingProject.Requests.Pojo.ProductPojoInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfoReq {
    @NotNull(message = "CUSTOMER ID REQUIRED")
    private int customerId;
    @NotNull(message = "STAFF ID REQUIRED")
    private int staffId;

    private List<@Valid ProductPojoInfo> products;

}
