package com.example.fashionmanager.service.impl.module_san_pham.chatlieu;

import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request.CreateChatLieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request.ListChatLieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request.UpdateChatLieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.response.ChatLieuDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.response.ChatLieuResponse;
import com.example.fashionmanager.entity.ChatLieuEntity;
import com.example.fashionmanager.service.CRUDService;

public interface ChatLieuService extends CRUDService<ChatLieuEntity, CreateChatLieuRequest, UpdateChatLieuRequest,
        ListChatLieuRequest, ChatLieuResponse, ChatLieuDetailResponse> {
}
