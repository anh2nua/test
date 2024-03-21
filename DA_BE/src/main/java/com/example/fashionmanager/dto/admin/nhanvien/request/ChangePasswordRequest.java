package com.example.fashionmanager.dto.admin.nhanvien.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequest {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
