package com.example.fashionmanager.service.impl.module_san_pham.chatlieudaydeo;

import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.response.ChatLieuDayDeoResponse;
import com.example.fashionmanager.entity.ChatLieuDayDeoEntity;
import com.example.fashionmanager.service.CRUDService;

public interface ChatLieuDayDeoService extends CRUDService<ChatLieuDayDeoEntity, ChatLieuDayDeoCreateRequest, ChatLieuDayDeoUpdateRequest
        , ChatLieuDayDeoListRequest, ChatLieuDayDeoResponse,ChatLieuDayDeoResponse> {
}
