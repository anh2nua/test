package com.example.fashionmanager.dto.shop.binhluan.request;

import com.example.fashionmanager.dto.ListRequestDto;
import com.example.fashionmanager.enums.TrangThaiBinhLuanEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BinhLuanListRequest extends ListRequestDto {
    private Long idChiTietSanPham;
    private TrangThaiBinhLuanEnum trangThaiBinhLuanEnum;
}
