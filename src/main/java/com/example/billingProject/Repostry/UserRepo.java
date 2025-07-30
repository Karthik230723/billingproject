package com.example.billingProject.Repostry;

import com.example.billingProject.Entities.User;
import com.example.billingProject.Requests.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByEmailId(String emailId);
    boolean existsByMobileNo(String mobileNo);
    Optional<User> findByIdAndEmployeeType(int id, String employeeType);
    User findByemailId(String emailId );

}

