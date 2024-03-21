package com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request;

import com.example.fashionmanager.enums.KieuGiamGia;
import com.example.fashionmanager.enums.TrangThaiCTSPEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPhamRequest {
    private Long mauSacId;
    private Integer soLuongBan;

    private Integer soLuongTang;
    private BigDecimal giaBanNiemYet;

    private KieuGiamGia hinhThucGiamGia;

    private BigDecimal giaTriDuocGiam;

    private BigDecimal giaBanCuoiCung;

    private TrangThaiCTSPEnums trangThaiCTSP;



}
