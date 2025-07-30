package com.example.billingProject.Repostry;

import com.example.billingProject.Entities.BillingInfo;
import com.example.billingProject.Requests.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BillingInfoRepo extends JpaRepository<BillingInfo,Integer> {
   List<BillingInfo> findBycustomerId(int customerId);

}
