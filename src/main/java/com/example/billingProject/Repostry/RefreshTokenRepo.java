package com.example.billingProject.Repostry;

import com.example.billingProject.Entities.User;
import com.example.billingProject.Entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByrefreshToken(String refreshToken);

    Optional<RefreshToken> findByUser(User user);

}
