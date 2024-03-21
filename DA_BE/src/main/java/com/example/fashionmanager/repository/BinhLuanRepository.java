package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.BinhLuanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BinhLuanRepository extends JpaRepository<BinhLuanEntity, Long>, JpaSpecificationExecutor<BinhLuanEntity> {
}
