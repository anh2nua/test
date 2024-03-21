package com.example.fashionmanager.dto.admin.sanpham.quanlyctsp.response;

import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response.MauSacDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response.SanPhamDetailResponse;
import com.example.fashionmanager.enums.KieuGiamGia;
import com.example.fashionmanager.enums.TrangThaiCTSPEnums;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietSanPhamDetailResponse {
    private Long id;
    private SanPhamDetailResponse sanPhamDetailResponse;
    private MauSacDetailResponse mauSacResponse;
    private List<Long> hinhAnhIds;
    private Integer soLuongBan;
    private BigDecimal giaBanNiemYet;
    private KieuGiamGia hinhThucGiamGia;
    private BigDecimal giaTriDuocGiam;
    private BigDecimal giaBanCuoiCung;
    private TrangThaiCTSPEnums trangThaiCTSP;
}
