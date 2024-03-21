package com.example.fashionmanager.controller.admin.sanpham.quanlymausac;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.CreateMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.ListMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.UpdateMauSacRequest;
import com.example.fashionmanager.service.impl.module_san_pham.mausac.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "tenMau", required = false) String tenMau) {
        return mauSacService.getList(ListMauSacRequest.builder().tenMau(tenMau).size(size).page(page).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateMauSacRequest request) {
        //validation

        return mauSacService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateMauSacRequest request) {
        //validation

        return mauSacService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return mauSacService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return mauSacService.delete(deleteRequest);
    }
}
