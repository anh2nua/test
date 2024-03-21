package com.example.fashionmanager.dto.admin.user.request;

import com.example.fashionmanager.entity.UserRoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserCreateRequest {
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String userName;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String password;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String email;
    @NotNull(message = "Không để trống ")

    private Set<UserRoleEntity> userRoleEntities = new HashSet<>();
}
