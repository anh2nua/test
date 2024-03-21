package com.example.fashionmanager.service.impl.module_san_pham.kieukhoa;

import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.Response.KieuKhoaResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.CreateKieuKhoaRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.ListKieuKhoaRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.UpdateKieuKhoaRequest;
import com.example.fashionmanager.entity.KieuKhoaEntity;
import com.example.fashionmanager.service.CRUDService;

public interface KieuKhoaService extends CRUDService<KieuKhoaEntity, CreateKieuKhoaRequest, UpdateKieuKhoaRequest, ListKieuKhoaRequest, KieuKhoaResponse,KieuKhoaResponse> {
}
