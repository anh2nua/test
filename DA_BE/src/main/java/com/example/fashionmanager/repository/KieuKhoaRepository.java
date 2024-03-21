package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.KieuKhoaEntity;
import com.example.fashionmanager.entity.SoNganEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KieuKhoaRepository extends JpaRepository<KieuKhoaEntity,Long>, JpaSpecificationExecutor<KieuKhoaEntity> {
Optional<KieuKhoaEntity> findFirstByActiveIsTrueAndDeletedIsFalse();

    @Query("select o from KieuKhoaEntity o where (?1 is null or o.tenKieuKhoa like ?1) and o.deleted = false ")
    Page<KieuKhoaEntity> findAllKieuKhoa(String tenKieuKhoa, Pageable pageable);

}
