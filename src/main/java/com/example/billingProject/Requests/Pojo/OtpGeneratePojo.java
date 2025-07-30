package com.example.billingProject.Requests.Pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpGeneratePojo {
    @NotBlank(message = "Email id should not be empty")
    @Email(message = "Enter valid emailId")
    private String email;
}
