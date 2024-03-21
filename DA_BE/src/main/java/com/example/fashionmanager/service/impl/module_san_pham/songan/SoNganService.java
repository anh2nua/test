package com.example.fashionmanager.service.impl.module_san_pham.songan;

import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.response.SoNganResponse;
import com.example.fashionmanager.entity.SoNganEntity;
import com.example.fashionmanager.service.CRUDService;

public interface SoNganService extends CRUDService<SoNganEntity, SoNganCreateRequest, SoNganUpdateRequest, SoNganListRequest, SoNganResponse,SoNganResponse> {
}
