package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ChatLieuDayDeoEntity;
import com.example.fashionmanager.entity.KieuKhoaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatLieuDayDeoRepository extends JpaRepository<ChatLieuDayDeoEntity,Long>, JpaSpecificationExecutor<ChatLieuDayDeoEntity> {
    Optional<ChatLieuDayDeoEntity> findFirstByActiveIsTrueAndDeletedIsFalse();
    @Query("select o from ChatLieuDayDeoEntity o where (?1 is null or o.tenChatLieuDayDeo like ?1) and o.deleted = false ")
    Page<ChatLieuDayDeoEntity> findAllChatLieuDayDeo(String tenKieuKhoa, Pageable pageable);
}
