package com.example.billingProject.Controller;

import com.example.billingProject.Constants.ConstantMessages;
import com.example.billingProject.Entities.Product;
import com.example.billingProject.Exception.EmailExist;
import com.example.billingProject.Requests.ProductInfo;
import com.example.billingProject.Response.CommonErrorResponse;
import com.example.billingProject.Response.Response;
import com.example.billingProject.Entities.User;
import com.example.billingProject.Exception.UserNotFoundException;
import com.example.billingProject.Requests.UserInfo;
import com.example.billingProject.Service.AdminService;
import com.example.billingProject.Constants.StatusCode;
import com.example.billingProject.Service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    ProductService productService;
    @PostMapping("/adduser")
    public ResponseEntity<CommonErrorResponse> addEmployee(@Valid @RequestBody UserInfo request){
        if(adminService.isEmailExists(request.getEmailId())){
            throw new EmailExist(ConstantMessages.EMAIL_EXIST);
        }
        User user = new User();
        user.setName(request.getName());
        user .setMobileNo(request.getMobileNo());
        user.setEmailId(request.getEmailId());
        user.setEmployeeType(request.getEmployeeType());
        user.setPassword(request.getPassword());
        adminService.addEmployee(user);
        CommonErrorResponse response=new CommonErrorResponse(ConstantMessages.SUCCESS,StatusCode.STATUSOK,ConstantMessages.ADDMESSAGE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getuser")
    public ResponseEntity<Response> getEmployee(){
        List<User> users=adminService.getEmployee();
        Response response=new Response(ConstantMessages.SUCCESS,StatusCode.STATUSOK,ConstantMessages.DATAGET);
        response.setData(users);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<Response> Update(@PathVariable int id,@Valid @RequestBody UserInfo request){
        User user = adminService.getEmployeeById(id);
        if (user == null){
            throw new UserNotFoundException(ConstantMessages.USERNOTFOUND);
        }
        user.setName(request.getName());
        user.setMobileNo(request.getMobileNo());
        user.setEmailId(request.getEmailId());
        user.setEmployeeType(request.getEmployeeType());
        user.setPassword(request.getPassword());
        adminService.addEmployee(user);
        Response response=new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.MESSAGE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getuser/{id}")
    public ResponseEntity<Response> getId(@PathVariable int id){
        User user= adminService.getEmployeeById(id);
        if(user==null){
            throw new UserNotFoundException(ConstantMessages.USERNOTFOUND);
        }
        Response response=new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.GETIDUSERMESSAGE);
        response.setRecord(user);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<Response> deleteUserById(@PathVariable int id){
        User user =adminService.getEmployeeById(id);
        if(user==null){
            throw new UserNotFoundException(ConstantMessages.USERNOTFOUND);
        }
        adminService.deleteEmployeeById(id);
        Response response=new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.USERDELETE);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/addproduct")
    public ResponseEntity<CommonErrorResponse> addProduct(@Valid @RequestBody ProductInfo request){
        Product product = new Product();
        product.setproductName(request.getProductName());
        product.setCost(request.getCost() );
        product.setDescription(request.getDescription());
        product.setProductType(request.getProductType());
        product.setQuantity(request.getQuantity());
        productService.addProduct(product);
        CommonErrorResponse response=new CommonErrorResponse(ConstantMessages.SUCCESS, StatusCode.STATUSOK,ConstantMessages.ADDPRODUCT);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getproduct")
    public ResponseEntity<Response>getProduct(){
        List<Product> product=productService.getProducts();
        Response response=new Response(ConstantMessages.SUCCESS,StatusCode.STATUSOK,ConstantMessages.DATAGET);
        response.setData(product);
        return ResponseEntity.ok(response);

    }
    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<Response> updateBy(@PathVariable int id,@Valid @RequestBody ProductInfo request){
        Product product= productService.getProductById(id);
        if(product==null){
            throw new UserNotFoundException(ConstantMessages.PRODUCTNOTFOUND);
        }
        product.setproductName(request.getProductName());
        product.setCost(request.getCost());
        product.setDescription(request.getDescription());
        product.setProductType(request.getProductType());
        product.setQuantity(request.getQuantity());
        productService.addProduct(product);
        Response response=new Response(ConstantMessages.SUCCESS,StatusCode.STATUSOK,ConstantMessages.UPDATED);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getproduct/{id}")
    public ResponseEntity<Response> getById(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product==null){
            throw new UserNotFoundException(ConstantMessages.PRODUCTNOTFOUND);
        }
        Response response=new Response(ConstantMessages.SUCCESS,StatusCode.STATUSOK,ConstantMessages.GETIDMESSAGE);
        response.setRecord(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<Response> deleteProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product==null) {
            throw new UserNotFoundException(ConstantMessages.USERNOTFOUND);
        }
        productService.deleteProductById(id);
        Response response = new Response(ConstantMessages.SUCCESS, StatusCode.STATUSOK, ConstantMessages.PRODUCTDELETE);
        return ResponseEntity.ok(response);
    }
}


