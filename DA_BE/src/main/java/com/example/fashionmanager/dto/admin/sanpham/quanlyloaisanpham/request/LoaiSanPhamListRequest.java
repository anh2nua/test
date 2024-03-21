package com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class LoaiSanPhamListRequest extends ListRequestDto {
    private String tenLoaiSanPham;
}
