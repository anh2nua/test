package com.example.fashionmanager.dto.shop.binhluan.request;

import com.example.fashionmanager.enums.DiemDanhGiaEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BinhLuanCreateRequest {
    private String hoTen;
    private String sdt;
    private String email;
    private String noiDung;
    private DiemDanhGiaEnum diemDanhGia;
    private Long chiTietSanPhamId;
}
