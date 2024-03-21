package com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListThuongHieuRequest extends ListRequestDto {
    private String tenThuongHieu;
}
