package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dia_chi_entity")
public class DiaChiEntity  extends CommonEntity implements Serializable {
    @Column(name = "id_tinh_thanh")
    private Integer idTinhThanh;
    @Column(name = "id_quan_huyen")
    private Integer idQuanHuyen;
    @Column(name = "id_phuong_xa")
    private String idPhuongXa;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "ma_buu_chinh")
    private Integer maBuuChinh;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Column(name = "ghi_chu")
    private String ghiChu;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHangEntity khachHang;
}
