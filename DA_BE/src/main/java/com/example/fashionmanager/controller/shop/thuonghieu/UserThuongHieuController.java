package com.example.fashionmanager.controller.shop.thuonghieu;

import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.ListThuongHieuRequest;
import com.example.fashionmanager.service.impl.module_san_pham.thuonghieu.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/thuong-hieu")
public class UserThuongHieuController {
    @Autowired
    ThuongHieuService thuongHieuService;
    @GetMapping("list")
    public ResponseEntity<?> getListThuongHieu(){
        return thuongHieuService.getList(ListThuongHieuRequest.builder().page(0).size(99999999).build());
    }
}
