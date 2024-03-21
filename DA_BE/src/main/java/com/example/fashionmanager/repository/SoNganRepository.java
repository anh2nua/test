package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.SoNganEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoNganRepository extends JpaRepository<SoNganEntity, Long>, JpaSpecificationExecutor<SoNganEntity> {

    Optional<SoNganEntity> findFirstByActiveIsTrueAndDeletedIsFalse();

    @Query("select o from SoNganEntity o where (?1 is null or o.tenSoNgan like ?1) and o.deleted = false ")
    Page<SoNganEntity> findAllSoNgan(String tenSoNgan, Pageable pageable);
}
