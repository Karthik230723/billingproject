package com.example.billingProject.Requests.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingPojo {
    private int billId;
    private int staffId;
    private int customerId;
    private double total;
    private List<PurchasePojo> purchasedProducts;
}
