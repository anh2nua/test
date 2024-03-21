package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.HoaTietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoaTietRepository extends JpaRepository<HoaTietEntity,Long>, JpaSpecificationExecutor<HoaTietEntity> {
    @Override
    long count();
    Optional<HoaTietEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
}
