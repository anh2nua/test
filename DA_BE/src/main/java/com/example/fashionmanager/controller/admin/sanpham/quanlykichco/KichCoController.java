package com.example.fashionmanager.controller.admin.sanpham.quanlykichco;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KichCoCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KichCoListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KickCoUpdateRequest;
import com.example.fashionmanager.service.impl.module_san_pham.kichco.KichCoService;
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
@RequestMapping("admin/san-pham/quan-ly-kich-co")
public class KichCoController {
    @Autowired
    KichCoService kichCoService;
    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page",defaultValue = "0")int page,
            @RequestParam(name = "size",defaultValue = "10")int size,
            @RequestParam(name = "tenKichCo",required = false)String  tenKichCo
    ){
        return kichCoService.getList(KichCoListRequest.builder()
                .tenKichCo(tenKichCo)
                .size(size)
                .page(page)
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody KichCoCreateRequest request) {
        //validation

        return kichCoService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody KickCoUpdateRequest request) {
        //validation

        return kichCoService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        return kichCoService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return kichCoService.delete(deleteRequest);
    }
}
