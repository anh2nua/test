package com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response;

import com.example.fashionmanager.enums.KieuGiamGia;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.enums.LoaiUuDaiDDG;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DotGiamGiaResponseDetail {
    private Long id;
    private String tenDotGiamGia;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    private LoaiUuDaiDDG loaiUuDaiDDG;
    private BigDecimal soTienHoaDonYeuCau;
    private KieuGiamGia loaiGiamGiaHD;

    private BigDecimal giaTriGiamHD;

    private DotGiamGiaStatus dotGiamGiaStatus;

    private List<SanPhamApDungResponse> sanPhamApDungResponses = new ArrayList<>();
}
