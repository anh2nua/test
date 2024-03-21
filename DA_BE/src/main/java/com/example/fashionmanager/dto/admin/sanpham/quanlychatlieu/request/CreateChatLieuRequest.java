package com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request;

import com.example.fashionmanager.dto.HistoryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateChatLieuRequest extends HistoryRequestDto {
    private String tenChatLieu;
    private String moTa;
}
