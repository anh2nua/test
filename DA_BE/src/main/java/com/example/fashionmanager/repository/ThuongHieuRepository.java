package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ThuongHieuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieuEntity,Long>, JpaSpecificationExecutor<ThuongHieuEntity> {
    @Override
    long count();
    Optional<ThuongHieuEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
}
