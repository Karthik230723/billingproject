package com.example.billingProject.Service;

import com.example.billingProject.Constants.ConstantMessages;
import com.example.billingProject.Entities.BillingInfo;
import com.example.billingProject.Entities.Product;
import com.example.billingProject.Entities.PurchasedProducts;
import com.example.billingProject.Entities.User;
import com.example.billingProject.Exception.UserNotFoundException;
import com.example.billingProject.Repostry.*;
import com.example.billingProject.Requests.Pojo.ProductPojoInfo;
import com.example.billingProject.Requests.ProductInfo;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    ProductRepo productRepo;
    @Autowired
    BillingInfoRepo billingInfoRepo;
    @Autowired
    UserRepo userRepo;


    public User addCustomer(User employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return userRepo.save(employee);
    }

    public boolean isEmailExists(String emailId) {
        return userRepo.existsByEmailId(emailId);
    }

    public boolean isMobileNoExists(String mobileNo) {
        return userRepo.existsByMobileNo(mobileNo);
    }

    public Optional<User> findByCustomerId(int customerid) {
        return userRepo.findByIdAndEmployeeType(customerid, "customer");
    }

    public Optional<User> findByStaffId(int staffid) {
        return userRepo.findByIdAndEmployeeType(staffid, "staff");
    }

    public Product findProductById(int id) {
        Optional<Product> product = productRepo.findById(id);
        return product.isPresent()?product.get():null;
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public BillingInfo saveBillingInfo(BillingInfo billingInfo) {
        return billingInfoRepo.save(billingInfo);
    }
    public List<BillingInfo> getBillingDetails() {
        return billingInfoRepo.findAll();
    }

@Transactional
    public List<PurchasedProducts> processPurchasedProducts(List<ProductPojoInfo> products, BillingInfo bill) {
        List<PurchasedProducts> purchasedProducts=new ArrayList<>();
        for (ProductPojoInfo productInfo : products) {
            Product product = findProductById(productInfo.getId());

            if (product.getQuantity() < productInfo.getQuantity()){
                throw new RuntimeException(ConstantMessages.QUANTITYOUTOFSTACK);
            }
            product.setQuantity(product.getQuantity() - productInfo.getQuantity());
            saveProduct(product);
            PurchasedProducts purchasedProduct = new PurchasedProducts();
            purchasedProduct.setProduct(product);
            purchasedProduct.setQuantity(productInfo.getQuantity());
            purchasedProduct.setBillingInfo(bill);
            purchasedProducts.add(purchasedProduct);
        }
        return purchasedProducts;
    }
}






