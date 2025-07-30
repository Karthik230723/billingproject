package com.example.billingProject.Controller;

import com.example.billingProject.Constants.ConstantMessages;
import com.example.billingProject.Constants.StatusCode;
import com.example.billingProject.Entities.BillingInfo;
import com.example.billingProject.Entities.Product;
import com.example.billingProject.Entities.PurchasedProducts;
import com.example.billingProject.Entities.User;
import com.example.billingProject.Exception.EmailExist;
import com.example.billingProject.Exception.MobileNo;
import com.example.billingProject.Exception.UserNotFoundException;
import com.example.billingProject.Requests.BillingInfoReq;
import com.example.billingProject.Requests.EmployeeInfo;
import com.example.billingProject.Response.Response;
import com.example.billingProject.Service.EmployeeService;
import com.example.billingProject.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/staff")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProductService productService;

    @PostMapping("/addcustomer")
    public ResponseEntity<Response>addCustomer(@Valid @RequestBody EmployeeInfo request){
        if(employeeService.isEmailExists(request.getEmailId())){
            throw new EmailExist(ConstantMessages.EMAIL_EXIST);
        } else if (employeeService.isMobileNoExists(request.getMobileNo())) {
            throw new MobileNo(ConstantMessages.MOBILENO_EXIST);
        }
        User employee = new User();
        employee.setName(request.getName());
        employee.setMobileNo(request.getMobileNo());
        employee.setEmailId(request.getEmailId());
        employee.setEmployeeType("customer");
        employee.setPassword(request.getPassword());
        employeeService.addCustomer(employee);
        Response response=new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK,ConstantMessages.CUSTOMER_ADD_MESSAGE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String HelloWorld(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "HELLOW" +" ";
    }

    @PostMapping("/billgenerate")
    public ResponseEntity<Response> createBill(@Valid @RequestBody BillingInfoReq request) {
        if (employeeService.findByCustomerId(request.getCustomerId()).isEmpty()){
            throw new UserNotFoundException(ConstantMessages.CUSTOMERIDTYPE);
        }

        if (employeeService.findByStaffId(request.getStaffId()).isEmpty()){
            throw new UserNotFoundException(ConstantMessages.STAFFIDTYPE);
        }
        BillingInfo bill = new BillingInfo();
        bill.setCustomerId(request.getCustomerId());
        bill.setStaffId(request.getStaffId());
        List<PurchasedProducts> purchasedProducts = employeeService.processPurchasedProducts(request.getProducts(), bill);
        double total = purchasedProducts.stream()
                .mapToDouble(product -> product.getProduct().getCost() * product.getQuantity())
                .sum();
        bill.setPurchasedProducts(purchasedProducts);
        bill.setTotal(total);
        employeeService.saveBillingInfo(bill);
        Response response=new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.BILL_GENERATED);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getbill")
    public ResponseEntity<Response>getBillingDetails(){
        List<BillingInfo> bill=employeeService.getBillingDetails();
        Response response=new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.BILL_GENERATED);
        response.setData(bill);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/search")
    public List<Product> getByName(@RequestParam("productName") String productName){
        return productService.getByName(productName);
    }
}

