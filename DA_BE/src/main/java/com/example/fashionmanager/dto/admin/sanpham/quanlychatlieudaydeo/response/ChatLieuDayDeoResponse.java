package com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatLieuDayDeoResponse {
    private Long id;
    private String tenChatLieuDayDeo;
}
