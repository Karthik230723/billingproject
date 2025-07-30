package com.example.billingProject.Repostry;

import com.example.billingProject.Entities.PurchasedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends JpaRepository<PurchasedProducts,Integer> {
}
