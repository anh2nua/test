package com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoaiSanPhamResponse {
private Long id;
private String tenLoaiSanPham;
}
