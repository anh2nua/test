package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.KieuGiamGia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "san_pham_ap_dung_ddg_entity")
public class SanPhamApDungDGGEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPhamEntity sanPhamEntity;
    @ManyToOne
    @JoinColumn(name = "dot_diam_gia_id")
    private DotGiamGiaEntity dotGiamGiaEntity;
    @Column(name = "loai_uu_dai")
    @Enumerated(EnumType.STRING)
    private KieuGiamGia loaiUuDai;

    @Column(name = "gia_tri_duoc_giam")
    private BigDecimal giaTriDuocGiam;

}
