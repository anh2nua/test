package com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class LoaiSanPhamUpdateRequest extends LoaiSanPhamCreateRequest{
private Long id;
}
