package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ChatLieuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ChatLieuRepository extends JpaRepository<ChatLieuEntity, Long>, JpaSpecificationExecutor<ChatLieuEntity> {
    @Override
    long count();
    Optional<ChatLieuEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
}
