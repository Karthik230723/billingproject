package com.example.billingProject.JwtSecurity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtGenerate {
    private static final String SECRETE_KEY_STRING="3F3hzOCOte48lFH6xN33eMtNq2J2MiUN";

    private final SecretKey SECRETE_KEY= Keys.hmacShaKeyFor(SECRETE_KEY_STRING.getBytes());

    public String  generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +1000 *60*60))
                 .signWith(SECRETE_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token,UserDetails userDetails){
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(SECRETE_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    private boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parser()
                .verifyWith(SECRETE_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expirationDate.before(new Date());
    }
}




