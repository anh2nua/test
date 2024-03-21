package com.example.fashionmanager.dto.admin.user.response;

import com.example.fashionmanager.entity.UserRoleEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Set<UserRoleEntity> userRoleEntities = new HashSet<>();
}
