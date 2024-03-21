package com.example.fashionmanager.service.impl.module_nhan_vien.quanlynhanvien;

import com.example.fashionmanager.dto.admin.nhanvien.request.ChangePasswordRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienCreateRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienListRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienUpdateRequest;
import com.example.fashionmanager.dto.admin.nhanvien.response.NhanVienDetailResponse;
import com.example.fashionmanager.dto.admin.nhanvien.response.NhanVienResponse;
import com.example.fashionmanager.entity.NhanVienEntity;
import com.example.fashionmanager.service.CRUDService;
import org.springframework.http.ResponseEntity;

public interface NhanVienService extends CRUDService<NhanVienEntity, NhanVienCreateRequest, NhanVienUpdateRequest, NhanVienListRequest, NhanVienResponse, NhanVienDetailResponse> {
ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest);

}
