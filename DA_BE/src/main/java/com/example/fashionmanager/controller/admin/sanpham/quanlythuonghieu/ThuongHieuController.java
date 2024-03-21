package com.example.fashionmanager.controller.admin.sanpham.quanlythuonghieu;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.CreateThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.ListThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.UpdateThuongHieuRequest;
import com.example.fashionmanager.service.impl.module_san_pham.thuonghieu.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-thuong-hieu")
public class ThuongHieuController {
    @Autowired
    ThuongHieuService thuongHieuService;
    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenThuongHieu", required = false) String tenThuongHieu) {
        return thuongHieuService.getList(ListThuongHieuRequest.builder().tenThuongHieu(tenThuongHieu).size(size).page(page).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateThuongHieuRequest request) {
        //validation

        return thuongHieuService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateThuongHieuRequest request) {
        //validation

        return thuongHieuService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return thuongHieuService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return thuongHieuService.delete(deleteRequest);
    }
}
