package com.example.fashionmanager.service.impl.module_san_pham.quanlysanpham;

import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response.SanPhamDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response.SanPhamResponse;
import com.example.fashionmanager.entity.SanPhamEntity;
import com.example.fashionmanager.service.CRUDService;

public interface SanPhamService extends CRUDService<SanPhamEntity, SanPhamCreateRequest, SanPhamUpdateRequest,SanPhamListRequest
        , SanPhamResponse, SanPhamDetailResponse> {
}
