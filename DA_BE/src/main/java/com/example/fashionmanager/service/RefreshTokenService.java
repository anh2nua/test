package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.auth.response.TokenRefreshResponse;
import com.example.fashionmanager.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(String userName);

    TokenRefreshResponse getToken(String refreshToken);

    RefreshToken verifyExpiration(RefreshToken token);

    Integer deleteByUserId(Long userId);
}
