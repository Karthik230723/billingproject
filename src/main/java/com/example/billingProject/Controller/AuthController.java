package com.example.billingProject.Controller;

import com.example.billingProject.Constants.ConstantMessages;
import com.example.billingProject.Entities.User;
import com.example.billingProject.JwtSecurity.JwtGenerate;
import com.example.billingProject.Entities.RefreshToken;
import com.example.billingProject.Requests.Pojo.OtpGeneratePojo;
import com.example.billingProject.Requests.OtpVerificationReq;
import com.example.billingProject.Requests.Pojo.RefreshTokenPojo;
import com.example.billingProject.Requests.Pojo.TokenReq;
import com.example.billingProject.Requests.Pojo.UserReq;
import com.example.billingProject.Service.AdminService;
import com.example.billingProject.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private JwtGenerate jwtGenerate;
    @Autowired
    private AuthService authService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public TokenReq login( @RequestBody UserReq user) {
        RefreshToken refreshToken = authService.createToken(user.getEmailId());
        User logUser = adminService.findByEmailId(user.getEmailId());
        if (logUser == null) {
            throw new RuntimeException("Enter valid email");
        }
        if (!passwordEncoder.matches(user.getPassword(), logUser.getPassword())) {
            throw new RuntimeException("password Invalid");
        }
        return TokenReq.builder()
                .acessToken(jwtGenerate.generateToken(user.getEmailId()))
                .refToken(refreshToken.getRefreshToken()).build();
    }


    @PostMapping("/refreshtoken")
    public TokenReq refreshToken( @RequestBody RefreshTokenPojo refreshTokenPojo) {
        return authService.findByToken(refreshTokenPojo.getRefreshToken())
                .map(authService::verifyExpiry)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String acessToken = jwtGenerate.generateToken(user.getEmailId());
                    return TokenReq.builder()
                            .acessToken(acessToken)
                            .refToken(refreshTokenPojo.getRefreshToken())
                            .build();
                }).orElseThrow(()->new RuntimeException(ConstantMessages.INVALID_REFRESHTOKEN));
    }

    @PostMapping("/forgetpassword/initiate")
    public ResponseEntity<String> generateotp(@Valid @RequestBody OtpGeneratePojo request){
        String otp= authService.generateOtp(request.getEmail());
        return ResponseEntity.ok("otp generated" +" "+otp);
    }
    @PostMapping("/forgetpassword/verify")
    public ResponseEntity<String>resetPass(@Valid @RequestBody OtpVerificationReq request){
        return ResponseEntity.ok(authService.VerifyOtpandPassword(request.getOtp(), request.getNewPassword(), request.getEmail() ));
    }
}




