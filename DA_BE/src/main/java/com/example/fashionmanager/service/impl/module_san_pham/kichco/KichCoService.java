package com.example.fashionmanager.service.impl.module_san_pham.kichco;

import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KichCoCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KichCoListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KickCoUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.response.KichCoResponse;
import com.example.fashionmanager.entity.KichCoEntity;
import com.example.fashionmanager.service.CRUDService;

public interface KichCoService extends CRUDService<KichCoEntity, KichCoCreateRequest,
        KickCoUpdateRequest, KichCoListRequest, KichCoResponse,KichCoResponse> {
}
