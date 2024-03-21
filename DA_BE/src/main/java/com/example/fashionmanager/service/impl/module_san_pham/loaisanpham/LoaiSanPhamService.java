package com.example.fashionmanager.service.impl.module_san_pham.loaisanpham;

import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.response.LoaiSanPhamResponse;
import com.example.fashionmanager.entity.LoaiSanPhamEntity;
import com.example.fashionmanager.service.CRUDService;

public interface LoaiSanPhamService extends CRUDService<LoaiSanPhamEntity, LoaiSanPhamCreateRequest, LoaiSanPhamUpdateRequest, LoaiSanPhamListRequest, LoaiSanPhamResponse,LoaiSanPhamResponse> {
}
