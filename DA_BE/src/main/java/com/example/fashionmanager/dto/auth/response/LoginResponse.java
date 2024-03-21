package com.example.fashionmanager.dto.auth.response;

import com.example.fashionmanager.security.CustomUserDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private UserResponse userResponse;

    private String token;
    private String refreshToken;
}
