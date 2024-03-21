package com.example.fashionmanager.service.impl.auth;

import com.example.fashionmanager.dto.auth.response.TokenRefreshResponse;
import com.example.fashionmanager.entity.RefreshToken;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.jwt.impl.JwtService;
import com.example.fashionmanager.repository.RefreshTokenRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.security.CustomUserDetail;
import com.example.fashionmanager.service.RefreshTokenService;
import org.hibernate.query.sqm.TemporalUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${jwt.expiration-refresh-token}")
    private Long refreshTokenDurationMs;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName).get();
        RefreshToken refreshToken = refreshTokenRepository.findByUserEntity(userEntity).orElse(new RefreshToken());
        refreshToken.setUserEntity(userEntity);
        refreshToken.setExpiryDate(Instant.now().plus(refreshTokenDurationMs, ChronoUnit.SECONDS));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public TokenRefreshResponse getToken(String refreshTokenRequest) {
        CustomUserDetail customUserDetail = findByToken(refreshTokenRequest)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUserEntity)
                .map(CustomUserDetail::new)
                .orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Refresh token không tồn tại trong database")));
        final var roles = customUserDetail.getAuthorities();
        final var username = customUserDetail.getUsername();
        String token = jwtService.generateAccessToken(Map.of("role", roles), username);
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenRequest).get();
        refreshToken.setExpiryDate(Instant.now().plus(refreshTokenDurationMs, ChronoUnit.SECONDS));
        refreshTokenRepository.save(refreshToken);

        return TokenRefreshResponse.builder().accessToken(token).refreshToken(refreshTokenRequest).build();
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new FashionManagerException(new ErrorResponse(HttpStatus.FORBIDDEN, "Refresh token đã hết hạn,vui lòng đăng nhập lại"));
        }
        return token;
    }

    @Override
    public Integer deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUserEntity(userRepository.findById(userId).get());
    }
}
