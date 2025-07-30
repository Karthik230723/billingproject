package com.example.billingProject.Service;

import com.example.billingProject.Entities.Product;
import com.example.billingProject.Repostry.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepo.findById(id);
        return product.isPresent() ? product.get() : null;
    }

    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> getByName(String productName) {
       return productRepo.findByProductName(productName);
    }

    public boolean existsByProductId(Integer productId){
    return productRepo.existsById(productId);
    }
}

