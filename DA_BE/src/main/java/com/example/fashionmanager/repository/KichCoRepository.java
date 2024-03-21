package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.KichCoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KichCoRepository extends JpaRepository<KichCoEntity,Long>, JpaSpecificationExecutor<KichCoEntity> {
}
