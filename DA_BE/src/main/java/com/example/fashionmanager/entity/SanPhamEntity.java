package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "san_pham_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SanPhamEntity extends CommonEntity implements Serializable {
    @Column(name = "ma_san_pham")
    private String maSanPham;
    @Column(name = "ten_san_pham")
    private String tenSanPham;
    @ManyToOne
    @JoinColumn(name = "loai_san_pham_id")
    private LoaiSanPhamEntity loaiSanPhamEntity;
    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieuEntity thuongHieuEntity;
    @ManyToOne
    @JoinColumn(name = "chat_lieu_id")
    private ChatLieuEntity chatLieuEntity;

    @ManyToOne
    @JoinColumn(name = "hoa_tiet_id")
    private HoaTietEntity hoaTietEntity;

    @ManyToOne
    @JoinColumn(name = "kich_thuoc_id")
    private KichThuocEntity kichThuocEntity;

    @ManyToOne
    @JoinColumn(name = "chat_lieu_day_deo_id")
    private ChatLieuDayDeoEntity chatLieuDayDeoEntity;

    @ManyToOne
    @JoinColumn(name = "kieu_khoa_id")
    private KieuKhoaEntity kieuKhoaEntity;
    @ManyToOne
    @JoinColumn(name = "so_ngan_id")
    private SoNganEntity  soNganEntity;

    @Column(name = "phu_hop_su_dung",columnDefinition = "LONGTEXT")
    private String phuHopSuDung;
    @Column(name = "mo_ta", columnDefinition = "LONGTEXT")
    private String moTa;
    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SanPhamDanhMucEntity> sanPhamDanhMucEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ChiTietSanPhamEntity> chiTietSanPhamEntities = new HashSet<>();

}
