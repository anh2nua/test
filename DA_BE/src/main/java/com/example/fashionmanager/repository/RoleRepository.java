package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.RoleEntity;
import com.example.fashionmanager.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    List<RoleEntity>  findAllByRoleNameInAndActiveIsTrueAndDeletedIsFalse(List<RoleEnums> roleEnumsList);
}
