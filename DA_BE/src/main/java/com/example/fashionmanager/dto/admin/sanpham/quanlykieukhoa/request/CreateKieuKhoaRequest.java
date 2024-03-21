package com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateKieuKhoaRequest {
    private String tenKieuKhoa;
    private String moTa;
}
