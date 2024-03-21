package com.example.fashionmanager.controller.admin.sanpham.quanlysongan;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganUpdateRequest;
import com.example.fashionmanager.service.impl.module_san_pham.songan.SoNganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-so-ngan")
public class SoNganController {
    @Autowired
    private SoNganService soNganService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "tenSoNgan", required = false) String tenSoNgan
    ){
        return soNganService.getList(SoNganListRequest.builder()
                .tenSoNgan(tenSoNgan)
                .page(page)
                .size(size).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SoNganCreateRequest request){
        return soNganService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SoNganUpdateRequest request){
        return soNganService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return soNganService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return soNganService.delete(deleteRequest);
    }
}
