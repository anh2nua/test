package com.example.fashionmanager.controller.admin.sanpham.quanlydanhmuc;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucUpdateRequest;
import com.example.fashionmanager.service.impl.module_san_pham.danhmuc.DanhMucService;
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
@RequestMapping("admin/san-pham/quan-ly-danh-muc")
public class DanhMucController {
    @Autowired
    DanhMucService danhMucService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "maDanhMuc", required = false) String maDanhMuc,
            @RequestParam(name = "tenDanhMuc", required = false) String tenDanhMuc,
            @RequestParam(name = "idDanhMucParent", required = false) Long idDanhMucParent,
            @RequestParam(name = "isParent", required = false) Boolean isParent

    ) {
        return danhMucService.getList(DanhMucListRequest.builder()
                .maDanhMuc(maDanhMuc)
                .tenDanhMuc(tenDanhMuc)
                .idDanhMucParent(idDanhMucParent)
                .isParent(isParent)
                .page(page)
                .size(size).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DanhMucCreateRequest request) {
        return danhMucService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody DanhMucUpdateRequest request) {
        return danhMucService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return danhMucService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return danhMucService.delete(deleteRequest);
    }
}
