package com.example.billingProject.SecurityConfiguration;

import com.example.billingProject.Entities.User;

import com.example.billingProject.Repostry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        User user=userRepo.findByemailId(emailId);
        if(user ==null){
            throw new UsernameNotFoundException("Email Id not found");
        }
         return new UserPrincipals(user);

    }
}
