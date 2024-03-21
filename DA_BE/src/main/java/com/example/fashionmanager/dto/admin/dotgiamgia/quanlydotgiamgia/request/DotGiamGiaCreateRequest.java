package com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request;

import com.example.fashionmanager.enums.KieuGiamGia;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.enums.LoaiUuDaiDDG;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class DotGiamGiaCreateRequest {
    private String tenDotGiamGia;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private LoaiUuDaiDDG loaiUuDaiDDG;

    private List<SanPhamApDungDGGRequest> sanPhamApDungDGGs;
    private BigDecimal soTienHoaDonYeuCau;

    private KieuGiamGia loaiGiamGiaHD;

    private BigDecimal giaTriGiamHD;
    private DotGiamGiaStatus dotGiamGiaStatus;


}
