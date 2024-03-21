package com.example.fashionmanager.controller.admin.nhanvien;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.ChangePasswordRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienCreateRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienListRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienUpdateRequest;
import com.example.fashionmanager.service.impl.module_nhan_vien.quanlynhanvien.NhanVienService;
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

@RestController
@RequestMapping("admin/nhan-vien/quan-ly-nhan-vien")
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "tenNhanVien", required = false) String tenNhanVien,
            @RequestParam(name = "soDienThoai", required = false) String soDienThoai,
            @RequestParam(name = "gioiTinh", required = false) Boolean gioiTinh
    ) {
        return nhanVienService.getList(NhanVienListRequest
                .builder()
                .tenNhanVien(tenNhanVien)
                .soDienThoai(soDienThoai)
                .gioiTinh(gioiTinh)
                .page(page)
                .size(size)
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NhanVienCreateRequest request) {
        //validation

        return nhanVienService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody NhanVienUpdateRequest request) {
        //validation

        return nhanVienService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return nhanVienService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return nhanVienService.delete(deleteRequest);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        //validation

        return nhanVienService.changePassword(request);
    }

    @PutMapping("/change-active")
    public ResponseEntity<?> changeActive(@RequestBody ActiveRequest request) {
        //validation

        return nhanVienService.changeActive(request);
    }
}
