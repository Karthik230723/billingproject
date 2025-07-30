package com.example.billingProject.Repostry;

import com.example.billingProject.Entities.OtpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OtpRepo extends JpaRepository<OtpInfo,Integer> {
   Optional<OtpInfo>findByEmail(String email);
}
