package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.auth.request.LoginRequest;
import com.example.fashionmanager.dto.auth.response.LoginResponse;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;

public interface IUserService {
    LoginResponse authenticate(LoginRequest request) throws Exception;
    void add(UserEntity userEntity, UserRoleEntity userRoleEntity);
}
