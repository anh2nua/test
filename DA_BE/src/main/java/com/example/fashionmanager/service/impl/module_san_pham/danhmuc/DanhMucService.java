package com.example.fashionmanager.service.impl.module_san_pham.danhmuc;

import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response.DanhMucDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response.DanhMucResponse;
import com.example.fashionmanager.entity.DanhMucEntity;
import com.example.fashionmanager.service.CRUDService;

import java.util.List;

public interface DanhMucService extends CRUDService<DanhMucEntity, DanhMucCreateRequest
        , DanhMucUpdateRequest, DanhMucListRequest, DanhMucResponse, DanhMucDetailResponse> {
    List<DanhMucDetailResponse> getDanhMucParent();
    List<DanhMucDetailResponse> getDanhMucChild();
}
