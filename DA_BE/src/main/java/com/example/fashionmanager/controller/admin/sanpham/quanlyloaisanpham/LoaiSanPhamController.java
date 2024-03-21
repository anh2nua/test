package com.example.fashionmanager.controller.admin.sanpham.quanlyloaisanpham;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamUpdateRequest;
import com.example.fashionmanager.service.impl.module_san_pham.loaisanpham.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-loai-san-pham")
public class LoaiSanPhamController {
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "tenLoai", required = false) String tenLoai
    ){
        return loaiSanPhamService.getList(LoaiSanPhamListRequest.builder()
                .tenLoaiSanPham(tenLoai)
                .page(page)
                .size(size).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LoaiSanPhamCreateRequest request){
        return loaiSanPhamService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody LoaiSanPhamUpdateRequest request){
        return loaiSanPhamService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return loaiSanPhamService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return loaiSanPhamService.delete(deleteRequest);
    }
}
