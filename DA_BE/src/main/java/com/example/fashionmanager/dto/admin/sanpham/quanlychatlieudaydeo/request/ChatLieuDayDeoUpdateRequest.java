package com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ChatLieuDayDeoUpdateRequest extends ChatLieuDayDeoCreateRequest {
    private Long id;
}
