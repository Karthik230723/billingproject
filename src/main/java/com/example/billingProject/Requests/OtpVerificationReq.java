package com.example.billingProject.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationReq {
    @NotBlank(message = "OTP has to be entered")
    private String otp;
    @NotBlank(message = "New Password has to be entered")
    private String newPassword;
    @NotBlank(message = "Email id should not be empty")
    @Email(message = "Enter valid emailId")
    private String email;

}
