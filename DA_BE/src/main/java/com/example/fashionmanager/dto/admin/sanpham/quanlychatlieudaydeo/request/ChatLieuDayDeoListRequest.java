package com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ChatLieuDayDeoListRequest extends ListRequestDto {
    private String tenChatLieuDayDeo;

}
