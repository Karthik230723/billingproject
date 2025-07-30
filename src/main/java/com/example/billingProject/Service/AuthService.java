package com.example.billingProject.Service;

import com.example.billingProject.Constants.ConstantMessages;
import com.example.billingProject.Entities.OtpInfo;
import com.example.billingProject.Entities.User;
import com.example.billingProject.Exception.UserNotFoundException;
import com.example.billingProject.Entities.RefreshToken;
import com.example.billingProject.Repostry.OtpRepo;
import com.example.billingProject.Repostry.RefreshTokenRepo;
import com.example.billingProject.Repostry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthService {
    @Autowired
    RefreshTokenRepo refreshTokenRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    OtpRepo otpRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    //BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);

    public RefreshToken createToken(String emailId){
        User user = userRepo.findByemailId(emailId);
        Optional<RefreshToken> existingToken = refreshTokenRepo.findByUser(user);
        RefreshToken refreshToken=new RefreshToken();
        if (existingToken.isPresent()) {
            refreshToken=existingToken.get();
            refreshToken.setRefreshToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now());

        }else{
         refreshToken=RefreshToken.builder()
                .user(userRepo.findByemailId(emailId))
                .refreshToken(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(120000))
                .build();
        }
        return refreshTokenRepo.save(refreshToken);
    }
    public Optional<RefreshToken>findByToken(String token){
        return refreshTokenRepo.findByrefreshToken(token);
    }

    public RefreshToken verifyExpiry(RefreshToken refToken){
        if(refToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepo.delete(refToken);
            throw new RuntimeException((ConstantMessages.REFRESHTOKEN_EXPIRED));
        }
        return refToken;
    }

    public String generateOtp(String email){
        User user=userRepo.findByemailId(email);
        if(user==null){
            throw new UserNotFoundException(ConstantMessages.USERNOTFOUND);
        }
        String otp=String.valueOf((int)(Math.random()*9000)+1000);

        OtpInfo otpInfo=otpRepo.findByEmail(email).orElse(new OtpInfo());
        otpInfo.setEmail(email);
        otpInfo.setOtp(otp);
        otpInfo.setExpiry(LocalDateTime.now().plusMinutes(1));
        otpRepo.save(otpInfo);
        return otp;
    }
    public String VerifyOtpandPassword(String otp,String newPassword,String email){
        OtpInfo otpInfo=otpRepo.findByEmail(email).orElseThrow(()->new UserNotFoundException("Email not found"));
        if(otpInfo.getExpiry().isBefore(LocalDateTime.now())){
            otpRepo.delete(otpInfo);
            throw new UserNotFoundException(ConstantMessages.OTP_EXPIRED);
        }
        if(!otpInfo.getOtp().equals(otp)){
            throw new UserNotFoundException(ConstantMessages.OTPNOTFOUND);
        }
        User user=userRepo.findByemailId(email);
        if(user==null){
            throw new UserNotFoundException(ConstantMessages.USERNOTFOUND);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        otpRepo.delete(otpInfo);
        return ConstantMessages.PASSWORD_CHANGED;
    }
}


