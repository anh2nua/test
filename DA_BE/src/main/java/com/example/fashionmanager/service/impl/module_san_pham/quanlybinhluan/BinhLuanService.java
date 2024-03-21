package com.example.fashionmanager.service.impl.module_san_pham.quanlybinhluan;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.shop.binhluan.request.ApproveRequest;
import com.example.fashionmanager.dto.shop.binhluan.request.BinhLuanCreateRequest;
import com.example.fashionmanager.dto.shop.binhluan.request.BinhLuanListRequest;
import org.springframework.http.ResponseEntity;

public interface BinhLuanService {
    ResponseEntity<?> createBinhLuan(BinhLuanCreateRequest request);

    ResponseEntity<?> approveBinhLuan(ApproveRequest request);

    ResponseEntity<?> deleteBinhLuan(DeleteRequest request);

    ResponseEntity<?> getListBinhLuan(BinhLuanListRequest request);

    ResponseEntity<?> detailBinhLuan(Long id);
}
