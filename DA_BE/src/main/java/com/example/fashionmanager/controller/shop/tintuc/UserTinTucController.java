package com.example.fashionmanager.controller.shop.tintuc;

import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucListRequest;
import com.example.fashionmanager.enums.SanPhamOrderBy;
import com.example.fashionmanager.service.impl.module_san_pham.quanlysanpham.SanPhamService;
import com.example.fashionmanager.service.impl.module_san_pham.tintuc.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/tin-tuc")
public class UserTinTucController {
    @Autowired
    TinTucService tinTucService;

    @GetMapping("danh-sach")
    public ResponseEntity<?> getListTinTuc(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "image", required = false) String image,
            @RequestParam(name = "introduction", required = false) String introduction,
            @RequestParam(name = "content", required = false) String content
    ) {
        return tinTucService.getList(TinTucListRequest.builder()
                .title(title)
                .image(image)
                .introduction(introduction)
                .content(content)
                .page(page)
                .size(size)
                .build());
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getDetailTinTuc(@PathVariable Long id){
        return tinTucService.getById(id);
    }
}
