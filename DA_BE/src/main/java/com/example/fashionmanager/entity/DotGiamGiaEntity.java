package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.KieuGiamGia;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.enums.LoaiUuDaiDDG;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dot_giam_gia_entity")
public class DotGiamGiaEntity extends CommonEntity implements Serializable {
    @Column(name = "ten_dot_giam_gia")
    private String tenDotGiamGia;
    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "loai_uu_dai")
    @Enumerated(EnumType.STRING)
    private LoaiUuDaiDDG loaiUuDaiDDG;
    @Column(name = "so_tien_hoa_don_yeu_cau")
    private BigDecimal soTienHoaDonYeuCau;
    @Column(name = "loai_giam_gia_hd")
    @Enumerated(EnumType.STRING)
    private KieuGiamGia loaiGiamGiaHD;

    @Column(name = "gia_tri_giam_hd")
    private BigDecimal giaTriGiamHD;
    @Column(name = "trang_thai_dot_giam_gia")
    @Enumerated(EnumType.STRING)
    private DotGiamGiaStatus dotGiamGiaStatus;

    @OneToMany(mappedBy = "dotGiamGiaEntity",fetch = FetchType.LAZY)
    private Set<SanPhamApDungDGGEntity> sanPhamApDungDGGEntities = new HashSet<>();

}
