//package com.example.fashionmanager.entity;
//
//import com.example.fashionmanager.entity.common.CommonEntity;
//import com.example.fashionmanager.enums.OrderStatus;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "order_product_entity")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@SuperBuilder
//public class HoaDonChiTietEntity extends CommonEntity implements Serializable {
//    @ManyToOne
//    @JoinColumn(name = "san_pham_id")
//    private SanPhamEntity sanPhamEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "hoa_don_id")
//    private HoaDonEntity hoaDonEntity;
//
//    @Column(name = "gia_ban")
//    private BigDecimal giaBan;
//
//    @Column(name = "so_luong")
//    private Integer soLuong;
//
//    @Column(name = "tong_tien")
//    private BigDecimal tongTien;
//
//    @Column(name = "trang_thai")
//    @Enumerated(EnumType.STRING)
//    private OrderStatus trangThai;
//
//
//}
