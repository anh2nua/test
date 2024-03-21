package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.HangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HangRepository extends JpaRepository<HangEntity,Long>, JpaSpecificationExecutor<HangEntity> {
    boolean existsByMaHangAndDeleted(String ma,Boolean delete);
    boolean existsByMaHangAndDeletedAndIdNot(String ma, Boolean delete, Long id);
}
