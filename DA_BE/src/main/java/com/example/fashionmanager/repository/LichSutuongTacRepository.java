package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.LichSuTuongTacEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LichSutuongTacRepository extends JpaRepository<LichSuTuongTacEntity, Long>, JpaSpecificationExecutor<LichSuTuongTacEntity> {
}
