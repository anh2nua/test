package com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateThuongHieuRequest extends CreateThuongHieuRequest {
    private Long id;
}
