package com.example.billingProject.Service;


import com.example.billingProject.Entities.User;
import com.example.billingProject.Repostry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;

    public void addEmployee(User employee)
    {    employee.setPassword(passwordEncoder.encode(employee.getPassword()));
         userRepo.save(employee);
    }
    public boolean isEmailExists(String emailId){
        return userRepo.existsByEmailId(emailId);
    }
    public List<User> getEmployee() {
        return userRepo.findAll();
    }
    public User getEmployeeById(int id) {
        Optional <User> user = userRepo.findById(id);
        return user.isPresent() ? user.get() : null;
    }
    public void deleteEmployeeById(int id) {
            userRepo.deleteById(id);
    }
    public User findByEmailId(String emailId){
        return userRepo.findByemailId(emailId);
    }

}

