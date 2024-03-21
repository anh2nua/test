package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.RefreshToken;
import com.example.fashionmanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserEntity(UserEntity userEntity);

    @Modifying
    int deleteByUserEntity(UserEntity user);
}
