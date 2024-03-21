package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.TinTucEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TinTucRepository extends JpaRepository<TinTucEntity,Long>, JpaSpecificationExecutor<TinTucEntity> {
}
