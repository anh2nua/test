package com.example.fashionmanager.controller.admin.dotgiamgia;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaCreateRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaListRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaUpdateRequest;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.service.impl.module_san_pham.dot_giam_gia.DotGiamGiaService;
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

import java.time.LocalDate;

@RestController
@RequestMapping("admin/su-kien/quan-ly-dot-giam-gia")
public class DotGiamGiaController {
    @Autowired
    private DotGiamGiaService dotGiamGiaService;

    @GetMapping("list")
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                     @RequestParam(value = "tenDotGiamGia", required = false) String tenDotGiamGia,
                                     @RequestParam(value = "ngayBatDau", required = false) LocalDate ngayBatDau,
                                     @RequestParam(value = "ngayKetThuc", required = false) LocalDate ngayKetThuc,
                                     @RequestParam(value = "status", required = false) DotGiamGiaStatus status) {

        DotGiamGiaListRequest dotGiamGiaListRequest =
                DotGiamGiaListRequest.builder()
                        .dotGiamGiaStatus(status)
                        .ngayKetThuc(ngayKetThuc)
                        .ngayBatDau(ngayBatDau)
                        .page(page)
                        .size(size)
                        .tenDotGiamGia(tenDotGiamGia)
                        .build();
        return dotGiamGiaService.getList(dotGiamGiaListRequest);
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id){
        return dotGiamGiaService.getById(id);
    }
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody DotGiamGiaCreateRequest request){
        return dotGiamGiaService.create(request);
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody DotGiamGiaUpdateRequest request){
    return dotGiamGiaService.update(request);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return dotGiamGiaService.delete(deleteRequest);
    }
    @PutMapping("change-active/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id){
        return null;
    }
}
