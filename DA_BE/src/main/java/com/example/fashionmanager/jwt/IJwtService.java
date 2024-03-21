package com.example.fashionmanager.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJwtService {
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractUsername(String token);
    String generateAccessToken(Map<String, Object> extraClaims, String username);
}
