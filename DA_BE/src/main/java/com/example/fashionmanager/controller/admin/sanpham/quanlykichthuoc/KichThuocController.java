package com.example.fashionmanager.controller.admin.sanpham.quanlykichthuoc;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.CreateKichThuocRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.ListKichThuocRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.UpdateKichThuocRequest;

import com.example.fashionmanager.service.impl.module_san_pham.kichthuoc.KichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-kich-thuoc")
public class KichThuocController {
    @Autowired
    KichThuocService kichThuocService;

    @GetMapping("/list")
    public ResponseEntity<?>getList(
            @RequestParam(name = "page",defaultValue = "0")int page,
            @RequestParam(name = "size",defaultValue = "10")int size,
            @RequestParam(name = "kichCoId",required = false)Long kichCoId
    ){
        return kichThuocService.getList(ListKichThuocRequest.builder()
                .kichCoId(kichCoId)
                .size(size)
                .page(page)
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateKichThuocRequest request) {
        //validation

        return kichThuocService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateKichThuocRequest request) {
        //validation

        return kichThuocService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        return kichThuocService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return kichThuocService.delete(deleteRequest);
    }
}
