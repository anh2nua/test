package com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateChatLieuRequest extends CreateChatLieuRequest{
    private Long id;
}
