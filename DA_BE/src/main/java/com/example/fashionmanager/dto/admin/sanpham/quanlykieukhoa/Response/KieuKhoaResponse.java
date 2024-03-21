package com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class KieuKhoaResponse {
    private Long id;
    private String tenKieuKhoa;
}
