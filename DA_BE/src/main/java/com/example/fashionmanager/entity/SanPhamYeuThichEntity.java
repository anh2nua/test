//package com.example.fashionmanager.entity;
//
//import com.example.fashionmanager.entity.common.CommonEntity;
//import jakarta.persistence.Entity;
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
//
//@Entity
//@Table(name = "product_favorite_entity")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@SuperBuilder
//public class SanPhamYeuThichEntity extends CommonEntity implements Serializable {
//    @ManyToOne
//    @JoinColumn(name = "khach_hang_id")
//    private KhachHangEntity khachHangEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "san_pham_id")
//    private SanPhamEntity sanPhamEntity;
//}
