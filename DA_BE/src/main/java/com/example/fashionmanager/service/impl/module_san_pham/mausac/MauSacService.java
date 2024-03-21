package com.example.fashionmanager.service.impl.module_san_pham.mausac;

import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.CreateMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.ListMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.UpdateMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response.MauSacDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response.MauSacResponse;
import com.example.fashionmanager.entity.MauSacEntity;
import com.example.fashionmanager.service.CRUDService;

public interface MauSacService extends CRUDService<MauSacEntity, CreateMauSacRequest, UpdateMauSacRequest,
        ListMauSacRequest, MauSacResponse, MauSacDetailResponse> {
}
