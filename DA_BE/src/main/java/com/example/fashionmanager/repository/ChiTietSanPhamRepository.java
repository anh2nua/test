package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ChiTietSanPhamEntity;
import com.example.fashionmanager.entity.SanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPhamEntity, Long>, JpaSpecificationExecutor<ChiTietSanPhamEntity> {
    @Modifying
    @Query("DELETE FROM ChiTietSanPhamEntity ct WHERE ct.sanPhamEntity.id = ?1")
    void deleteAllByIdSanPham(Long id);
    Optional<SanPhamEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
}
