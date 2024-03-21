package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.KieuGiamGia;
import com.example.fashionmanager.enums.TrangThaiCTSPEnums;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chi_tiet_san_pham_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ChiTietSanPhamEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPhamEntity sanPhamEntity;

    @ManyToOne
    @JoinColumn(name = "mau_sac_id")
    private MauSacEntity mauSacEntity;


    @Column(name = "so_luong_ban")
    private Integer soLuongBan;
    @Column(name = "so_luong_tang")
    private Integer soLuongTang;

    @Column(name = "gia_ban_niem_yet")
    private BigDecimal giaBanNiemYet;

    @Column(name = "hinh_thuc_giam_gia")
    @Enumerated(EnumType.STRING)
    private KieuGiamGia hinhThucGiamGia; // Hình thức giảm giá( nếu có)
    @Column(name = "gia_tri_duoc_giam")
    private BigDecimal giaTriDuocGiam;// Giá trị được giảm

    @Column(name = "gia_ban_cuoi_cung")
    private BigDecimal giaBanCuoiCung; //Giá cuối cùng


    @Column(name = "trang_thai_ctsp")
    private TrangThaiCTSPEnums trangThaiCTSP;

    @OneToMany(mappedBy = "chiTietSanPhamEntity",fetch = FetchType.LAZY)
    private Set<HinhAnhEntity> hinhAnhEntities = new HashSet<>();

    @OneToMany(mappedBy = "chiTietSanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BinhLuanEntity> binhLuanEntities = new HashSet<>();




}
