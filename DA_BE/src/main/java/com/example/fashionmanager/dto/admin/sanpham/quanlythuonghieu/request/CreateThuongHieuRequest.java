package com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request;

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
public class CreateThuongHieuRequest
{
    private String tenThuongHieu;
}
