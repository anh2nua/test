package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    @Modifying
    @Query("delete UserRoleEntity  where userEntity = ?1")
    Integer deleteAllByUserEntity(UserEntity userEntity);
}
