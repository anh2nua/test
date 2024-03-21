package com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListKieuKhoaRequest extends ListRequestDto {
    private String tenKieuKhoa;
}
