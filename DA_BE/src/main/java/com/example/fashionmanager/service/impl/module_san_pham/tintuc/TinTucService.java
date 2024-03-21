package com.example.fashionmanager.service.impl.module_san_pham.tintuc;

import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.response.TinTucResponse;
import com.example.fashionmanager.entity.TinTucEntity;
import com.example.fashionmanager.service.CRUDService;

public interface TinTucService extends CRUDService<TinTucEntity, TinTucCreateRequest, TinTucUpdateRequest,
        TinTucListRequest, TinTucResponse, TinTucResponse> {
}
