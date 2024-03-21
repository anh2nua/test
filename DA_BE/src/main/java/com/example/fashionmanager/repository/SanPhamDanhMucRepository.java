package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.SanPhamDanhMucEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamDanhMucRepository extends JpaRepository<SanPhamDanhMucEntity,Long> {
    @Modifying
    @Query("DELETE FROM SanPhamDanhMucEntity ct WHERE ct.sanPhamEntity.id = ?1")
    void deleteAllByIdSanPham(Long id);
}
