package com.example.fashionmanager.dto.admin.sanpham.quanlyhoatiet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateHoaTietRequest {
    private String tenHoaTiet;
    private String moTa;
}
