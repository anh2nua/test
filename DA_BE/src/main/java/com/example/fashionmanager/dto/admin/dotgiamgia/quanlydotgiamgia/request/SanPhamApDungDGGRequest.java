package com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request;

import com.example.fashionmanager.enums.KieuGiamGia;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SanPhamApDungDGGRequest {
    private Long idSanPham;
    private KieuGiamGia loaiGiamGia;
    private BigDecimal giaTriDuocGiam;
}
