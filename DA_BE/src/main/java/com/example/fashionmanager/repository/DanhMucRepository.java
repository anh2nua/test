package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.DanhMucEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DanhMucRepository extends JpaRepository<DanhMucEntity,Long>, JpaSpecificationExecutor<DanhMucEntity> {
   List<DanhMucEntity> findAllByIsParentAndDeletedIsFalse(Boolean isParent);

}
