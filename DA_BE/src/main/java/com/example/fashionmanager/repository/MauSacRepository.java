package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.HangEntity;
import com.example.fashionmanager.entity.MauSacEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MauSacRepository extends JpaRepository<MauSacEntity,Long>, JpaSpecificationExecutor<MauSacEntity> {
    @Override
    long count();

    Optional<MauSacEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
}
