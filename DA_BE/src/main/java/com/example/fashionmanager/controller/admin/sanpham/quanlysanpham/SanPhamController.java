package com.example.fashionmanager.controller.admin.sanpham.quanlysanpham;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamUpdateRequest;
import com.example.fashionmanager.service.impl.module_san_pham.quanlysanpham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/san-pham/quan-ly-san-pham")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "maSanPham", required = false) String maSanPham,
            @RequestParam(name = "tenSanPham", required = false) String tenSanPham,
            @RequestParam(name = "danhMucIds", required = false) List<Long> danhMucIds,
            @RequestParam(name = "loaiSanPhamId", required = false) Long loaiSanPhamId,
            @RequestParam(name = "thuongHieuId", required = false) Long thuongHieuId) {
        return sanPhamService.getList(SanPhamListRequest.builder()
                .maSanPham(maSanPham)
                .tenSanPham(tenSanPham)
                .danhMucIds(danhMucIds)
                .loaiSanPhamId(loaiSanPhamId)
                .thuongHieuId(thuongHieuId)
                .size(size)
                .page(page).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SanPhamCreateRequest request) {
        //validation

        return sanPhamService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SanPhamUpdateRequest request) {
        //validation

        return sanPhamService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return sanPhamService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return sanPhamService.delete(deleteRequest);
    }
}
