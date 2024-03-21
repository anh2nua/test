package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.DotGiamGiaEntity;
import com.example.fashionmanager.entity.SanPhamApDungDGGEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamApDungDDGRepository extends JpaRepository<SanPhamApDungDGGEntity,Long> {
    void deleteAllByDotGiamGiaEntity(DotGiamGiaEntity dotGiamGiaEntity);
}
