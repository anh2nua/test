package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.KichThuocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuocEntity,Long>, JpaSpecificationExecutor<KichThuocEntity> {
    @Override
    long count();
Optional<KichThuocEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
}
