package com.example.billingProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "BillingInfo")
public class BillingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customerId;
    private double total;
    private int staffId;
@OneToMany(mappedBy = "billingInfo", cascade = CascadeType.ALL)
@JsonManagedReference
@JsonIgnore
private List<PurchasedProducts> purchasedProducts = new ArrayList<>();

    public BillingInfo(int id, double total, int customerId, int staffId) {
        this.id = id;
        this.total = total;
        this.customerId = customerId;
       this.staffId = staffId;
    }

    public BillingInfo() {
    }

}
