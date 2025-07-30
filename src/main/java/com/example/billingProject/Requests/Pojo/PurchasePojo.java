package com.example.billingProject.Requests.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasePojo {
    private String productName;
    private String productType;
    private int quantityPurchased;
    private double cost;
}
