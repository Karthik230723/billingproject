package com.example.billingProject.Repostry;

import com.example.billingProject.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{
    @Query(nativeQuery = true,
    value="SELECT * FROM product_inventory WHERE product_name LIKE CONCAT('%', :productName, '%')")
    List<Product> findByProductName(@Param("productName") String productName);
}
