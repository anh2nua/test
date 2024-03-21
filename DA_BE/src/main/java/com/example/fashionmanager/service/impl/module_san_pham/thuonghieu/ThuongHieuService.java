package com.example.fashionmanager.service.impl.module_san_pham.thuonghieu;

import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.CreateThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.ListThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.UpdateThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.response.ThuongHieuDetailReponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.response.ThuongHieuReponse;
import com.example.fashionmanager.entity.ThuongHieuEntity;
import com.example.fashionmanager.service.CRUDService;

public interface ThuongHieuService extends CRUDService<ThuongHieuEntity, CreateThuongHieuRequest, UpdateThuongHieuRequest,
        ListThuongHieuRequest, ThuongHieuReponse, ThuongHieuDetailReponse> {
}
