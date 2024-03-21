package com.example.fashionmanager.service.impl.module_san_pham.chitietsanpham;

import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamListRequest;
import org.springframework.http.ResponseEntity;

public interface ChiTietSanPhamService {
    ResponseEntity<?> getListSanPham(SanPhamListRequest sanPhamListRequest);
    ResponseEntity<?> getListSanPhamDetail(Long id);
}
