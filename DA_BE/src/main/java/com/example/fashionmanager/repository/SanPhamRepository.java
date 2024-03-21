package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.SanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPhamEntity,Long>, JpaSpecificationExecutor<SanPhamEntity> {
List<SanPhamEntity> findAllByIdInAndDeleted(List<Long> ids,boolean deleted);
boolean existsByMaSanPham(String maSanPham);
}
