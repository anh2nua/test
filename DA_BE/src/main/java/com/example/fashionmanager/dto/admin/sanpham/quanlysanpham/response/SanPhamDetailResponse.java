package com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response;

import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.response.ChatLieuResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.response.ChatLieuDayDeoResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response.KichThuocDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.Response.KieuKhoaResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.response.LoaiSanPhamResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.response.ThuongHieuReponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response.DanhMucDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlyhoatiet.response.HoaTietResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.response.SoNganResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamDetailResponse {
    private Long id;
    private String maSanPham;
    private String tenSanPham;
    private LoaiSanPhamResponse loaiSanPhamResponse;
    private ChatLieuResponse chatLieuResponse;
    private HoaTietResponse hoaTietResponse;
    private KichThuocDetailResponse kichThuocResponse;
    private ChatLieuDayDeoResponse chatLieuDayDeoResponse;
    private ThuongHieuReponse thuongHieuReponse;
    private KieuKhoaResponse kieuKhoaResponse;

    private SoNganResponse soNganResponse;

    private String phuHopSuDung;

    private String moTa;

    private List<DanhMucDetailResponse> danhMucApDung;

    private List<ChiTietSanPhamDetailResponse> chiTietSanPhamDetailResponses;
}
