package com.example.billingProject.Requests.Pojo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenPojo {
    //@NotNull(message = "REFRESH TOKEN SHOULD BE ENTERED")
    private String refreshToken;
}
