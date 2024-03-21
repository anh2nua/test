package com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamResponse {
private Long id;
private String maSanPham;
private String tenSanPham;
private String tenLoaiSanPham;
private byte[] anhDaiDien;
}
