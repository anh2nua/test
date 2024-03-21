package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ChatLieuDayDeoEntity;
import com.example.fashionmanager.entity.LoaiSanPhamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPhamEntity,Long>, JpaSpecificationExecutor<LoaiSanPhamEntity> {
Optional<LoaiSanPhamEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
    @Query("select o from LoaiSanPhamEntity o where (?1 is null or o.tenLoai like ?1) and o.deleted = false ")
    Page<LoaiSanPhamEntity> findAllLoaiSanPham(String tenLoai, Pageable pageable);
}
