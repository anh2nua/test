package com.example.fashionmanager.service.impl.module_san_pham.kichthuoc;

import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response.KichThuocDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response.KichThuocResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.CreateKichThuocRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.ListKichThuocRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.UpdateKichThuocRequest;
import com.example.fashionmanager.entity.KichThuocEntity;
import com.example.fashionmanager.service.CRUDService;

public interface KichThuocService extends CRUDService<KichThuocEntity, CreateKichThuocRequest, UpdateKichThuocRequest,
        ListKichThuocRequest, KichThuocResponse, KichThuocDetailResponse> {
}
