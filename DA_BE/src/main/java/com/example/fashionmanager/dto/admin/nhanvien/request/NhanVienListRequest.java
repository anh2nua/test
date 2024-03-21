package com.example.fashionmanager.dto.admin.nhanvien.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class NhanVienListRequest extends ListRequestDto {
    private String tenNhanVien;
    private String soDienThoai;
    private Boolean gioiTinh;
}
