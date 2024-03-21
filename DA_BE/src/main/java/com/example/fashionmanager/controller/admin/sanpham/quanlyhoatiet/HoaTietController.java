package com.example.fashionmanager.controller.admin.sanpham.quanlyhoatiet;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyhoatiet.request.CreateHoaTietRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyhoatiet.request.ListHoaTietRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyhoatiet.request.UpdateHoaTietRequest;
import com.example.fashionmanager.service.impl.module_san_pham.hoatiet.HoaTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-hoa-tiet")
public class HoaTietController {
    @Autowired
    private HoaTietService hoaTietService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "tenHoaTiet", required = false) String tenHoaTiet
    ){
        return hoaTietService.getList(ListHoaTietRequest.builder()
                .tenHoaTiet(tenHoaTiet)
                .page(page)
                .size(size).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody  CreateHoaTietRequest request){
        return hoaTietService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody  UpdateHoaTietRequest request){
        return hoaTietService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return hoaTietService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return hoaTietService.delete(deleteRequest);
    }
}
