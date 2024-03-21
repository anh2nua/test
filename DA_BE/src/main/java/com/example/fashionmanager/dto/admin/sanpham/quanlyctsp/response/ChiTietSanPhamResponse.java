package com.example.fashionmanager.dto.admin.sanpham.quanlyctsp.response;

import com.example.fashionmanager.enums.KieuGiamGia;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietSanPhamResponse {
    private Long id;
    private Long idSanPham;
    private String tenSanPham;
    private Long anhId;
    private BigDecimal giaNiemYet;
    private KieuGiamGia kieuGiamGia;
    private BigDecimal giaTriGiam;
    private BigDecimal giaCuoiCung;
}
