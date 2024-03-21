package com.example.fashionmanager.controller.admin.sanpham.quanlytintuc;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucUpdateRequest;
import com.example.fashionmanager.service.impl.module_san_pham.tintuc.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/san-pham/quan-ly-tin-tuc")
public class TinTucController {
    @Autowired
    private TinTucService tinTucService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "image", required = false) String image,
            @RequestParam(name = "introduction", required = false) String introduction,
            @RequestParam(name = "content", required = false) String content) {
        return tinTucService.getList(TinTucListRequest.builder()
                .title(title)
                .image(image)
                .introduction(introduction)
                .content(content)
                .size(size)
                .page(page).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute TinTucCreateRequest request) {
        return tinTucService.create(request);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute TinTucUpdateRequest request) {
        return tinTucService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return tinTucService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setId(id);
        deleteRequest.setDeleted(true);
        return tinTucService.delete(deleteRequest);
    }
}
