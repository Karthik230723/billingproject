package com.example.billingProject.Controller;

import com.example.billingProject.Constants.ConstantMessages;
import com.example.billingProject.Constants.StatusCode;
import com.example.billingProject.Entities.BillingInfo;
import com.example.billingProject.Entities.Product;
import com.example.billingProject.Entities.PurchasedProducts;
import com.example.billingProject.Exception.UserNotFoundException;
import com.example.billingProject.Requests.Pojo.BillingPojo;
import com.example.billingProject.Requests.Pojo.PurchasePojo;
import com.example.billingProject.Response.Response;
import com.example.billingProject.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getcustomerbill/{customerId}")
    public ResponseEntity<Response>billCustomerId(@PathVariable int customerId) {
        List<BillingInfo> customer = customerService.billCustomerId(customerId);
        if (customer.isEmpty()) {
            throw new UserNotFoundException(ConstantMessages.CUSTOMERIDNOTEXIST);
        }
    Response response = new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.BILL_FETCHED);
    response.setData(customer);
    return ResponseEntity.ok(response);
}

    @GetMapping("/getbill/{id}")
    public ResponseEntity<Response> getBillingDetailsById(@PathVariable int id){

        BillingInfo bill=customerService.getBillingDetailsById(id);

        List<PurchasePojo> purchasePojo= new ArrayList<>();

        for (PurchasedProducts purchasedProducts : bill.getPurchasedProducts()) {
            Product product = purchasedProducts.getProduct();
            PurchasePojo purchase = new PurchasePojo();
            purchase.setProductName(product.getproductName());
            purchase.setProductType(product.getProductType());
            purchase.setQuantityPurchased((int) purchasedProducts.getQuantity());
            purchase.setCost(product.getCost());
            purchasePojo.add(purchase);
        }
        BillingPojo response = new BillingPojo();
        response.setBillId(bill.getId());
        response.setCustomerId(bill.getCustomerId());
        response.setStaffId(bill.getStaffId());
        response.setTotal(bill.getTotal());
        response.setPurchasedProducts(purchasePojo);
        Response status=new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.BILL_FETCHED);
        status.setRecord(response);
        return ResponseEntity.ok(status);
    }

}
