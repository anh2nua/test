package com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request;


import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListChatLieuRequest extends ListRequestDto {
    private String tenChatLieu;
}
