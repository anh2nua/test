package com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response;

import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response.MauSacDetailResponse;
import com.example.fashionmanager.enums.KieuGiamGia;
import com.example.fashionmanager.enums.TrangThaiCTSPEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietSanPhamDetailResponse {
    private Long id;
    private MauSacDetailResponse mauSacResponse;
    private List<Long> hinhAnhIds;
    private Integer soLuongBan;
    private Integer soLuongTang;
    private BigDecimal giaBanNiemYet;
    private KieuGiamGia hinhThucGiamGia;
    private BigDecimal giaTriDuocGiam;
    private BigDecimal giaBanCuoiCung;
    private TrangThaiCTSPEnums trangThaiCTSP;


}
