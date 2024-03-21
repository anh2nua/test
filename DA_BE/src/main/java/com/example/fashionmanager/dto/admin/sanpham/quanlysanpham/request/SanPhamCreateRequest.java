package com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request;

import com.example.fashionmanager.dto.HistoryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamCreateRequest extends HistoryRequestDto {

    private String tenSanPham;

    private Long chatLieuId;

    private Long hoaTietId;

    private Long kichThuocId;

    private Long chatLieuDayDeoId;

    private Long kieuKhoaId;

    private Long soNganId;
    private Long thuongHieuId;

    private Long loaiSanPhamId;
    private String phuHopSuDung;

    private String moTa;

    private List<ChiTietSanPhamRequest> chiTietSanPhamRequests;
    private List<Long> danhMucIds;

    private String mota;


}
