package com.example.fashionmanager.controller.admin.sanpham.quanlykieukhoa;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.CreateKieuKhoaRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.ListKieuKhoaRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.UpdateKieuKhoaRequest;
import com.example.fashionmanager.service.impl.module_san_pham.kieukhoa.KieuKhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-kieu-khoa")
public class KieuKhoaController {
    @Autowired
    private KieuKhoaService kieuKhoaService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "tenKieuKhoa", required = false) String tenKieuKhoa
    ){
        return kieuKhoaService.getList(ListKieuKhoaRequest.builder()
                .tenKieuKhoa(tenKieuKhoa)
                .page(page)
                .size(size).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateKieuKhoaRequest request){
        return kieuKhoaService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateKieuKhoaRequest request){
        return kieuKhoaService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return kieuKhoaService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return kieuKhoaService.delete(deleteRequest);
    }
}
