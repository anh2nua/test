package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
Optional<UserEntity> findByEmail(String userName);
Optional<UserEntity> findByUserName(String userName);

    @Override
    long count();
}
