package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.NhanVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVienEntity, Long>, JpaSpecificationExecutor<NhanVienEntity> {
    Optional<NhanVienEntity> findByCccd(String cccd);

    Optional<NhanVienEntity> findByCccdAndIdNot(String cccd, Long id);

    @Query("select o from NhanVienEntity o where o.userEntity.userName = ?1")
    Optional<NhanVienEntity> findByUserName(String userName);
}
