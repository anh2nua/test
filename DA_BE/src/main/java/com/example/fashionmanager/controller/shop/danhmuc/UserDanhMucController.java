package com.example.fashionmanager.controller.shop.danhmuc;

import com.example.fashionmanager.service.impl.module_san_pham.danhmuc.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/danh-muc")
public class UserDanhMucController {
    @Autowired
    DanhMucService danhMucService;
    @GetMapping("parent")
    public ResponseEntity<?> getDanhMucParent(){
        return ResponseEntity.ok(danhMucService.getDanhMucParent());
    }
}
