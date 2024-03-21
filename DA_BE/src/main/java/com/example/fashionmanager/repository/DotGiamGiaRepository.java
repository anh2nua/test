package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.DotGiamGiaEntity;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGiaEntity,Long>, JpaSpecificationExecutor<DotGiamGiaEntity> {
    @Query(value = """
                select case when count(o)> 0 then true else false end from
                 DotGiamGiaEntity o where o.dotGiamGiaStatus = ?1 
                 and o.deleted = false and (?2 between o.ngayBatDau and o.ngayKetThuc)
                 and (?3 is null or o.id != ?3)
                 
            """)
    boolean existsByDotGiamGiaDangHoatDong(DotGiamGiaStatus dotGiamGiaStatus, LocalDate now,Long id);

    @Override
    long count();
}
