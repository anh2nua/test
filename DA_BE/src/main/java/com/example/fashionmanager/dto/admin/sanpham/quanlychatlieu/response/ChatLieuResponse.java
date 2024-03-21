package com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ChatLieuResponse {
    private Long id;
    private String tenChatLieu;
}
