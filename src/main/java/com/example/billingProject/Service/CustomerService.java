package com.example.billingProject.Service;

import com.example.billingProject.Entities.BillingInfo;
import com.example.billingProject.Entities.Product;
import com.example.billingProject.Repostry.BillingInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    BillingInfoRepo billingInfoRepo;

    public List<BillingInfo> billCustomerId(int customerId) {
        return billingInfoRepo.findBycustomerId(customerId);
    }

    public BillingInfo getBillingDetailsById(int id) {
        Optional<BillingInfo> billingInfo=billingInfoRepo.findById(id);
        return billingInfo.isPresent()?billingInfo.get():null;

    }
}
