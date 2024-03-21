package com.example.fashionmanager.controller.shop.sanpham;

import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamListRequest;
import com.example.fashionmanager.enums.SanPhamOrderBy;
import com.example.fashionmanager.service.impl.module_san_pham.quanlysanpham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/san-pham")
public class UserSanPhamController {
    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("danh-sach")
    public ResponseEntity<?> getListSanPham(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "tenSanPham", defaultValue = "") String tenSanPham,
            @RequestParam(value = "maSanPham", defaultValue = "") String maSanPham,
            @RequestParam(value = "thuongHieuId", defaultValue = "") Long thuongHieuId,
            @RequestParam(value = "loaiSanPhamId", defaultValue = "") Long loaiSanPhamId,
            @RequestParam(value = "danhMucIds", defaultValue = "") List<Long> danhMucIds,
            @RequestParam(value = "orderBy", defaultValue = "NGAYTAO") SanPhamOrderBy sanPhamOrderBy

    ) {
        return sanPhamService.getList(SanPhamListRequest.builder()
                .sanPhamOrderBy(sanPhamOrderBy)
                .loaiSanPhamId(loaiSanPhamId)
                .danhMucIds(danhMucIds)
                .tenSanPham(tenSanPham)
                .maSanPham(maSanPham)
                .thuongHieuId(thuongHieuId)
                .page(page)
                .size(size)
                .build());
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getDetailSanPham(@PathVariable Long id){
        return sanPhamService.getById(id);
    }
}
