package com.example.fashionmanager.controller.auth;

import com.example.fashionmanager.dto.auth.request.LoginRequest;
import com.example.fashionmanager.dto.auth.request.TokenRefreshRequest;
import com.example.fashionmanager.dto.auth.response.LoginResponse;
import com.example.fashionmanager.dto.auth.response.TokenRefreshResponse;
import com.example.fashionmanager.entity.RefreshToken;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.jwt.impl.JwtService;
import com.example.fashionmanager.security.CustomUserDetail;
import com.example.fashionmanager.service.IUserService;
import com.example.fashionmanager.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    IUserService iUserService;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/authenticate")
    public LoginResponse authenticate(@RequestBody LoginRequest loginRequest) throws Exception {
        return iUserService.authenticate(loginRequest);
    }

    @PostMapping("/refresh-token")
    public TokenRefreshResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) throws Exception {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.getToken(requestRefreshToken);
    }

    @GetMapping("/admin")
    public String hihi() throws Exception {
        return "hihihi";
    }
}
