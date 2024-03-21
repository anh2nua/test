package com.example.fashionmanager.controller.admin.sanpham.quanlyCLDD;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoUpdateRequest;
import com.example.fashionmanager.service.impl.module_san_pham.chatlieudaydeo.ChatLieuDayDeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/san-pham/quan-ly-chat-lieu-day-deo")
public class ChatLieuDayDeoController {
    @Autowired
    private ChatLieuDayDeoService chatLieuDayDeoService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999") int size,
            @RequestParam(name = "tenChatLieuDayDeo", required = false) String tenChatLieuDayDeo
    ){
        return chatLieuDayDeoService.getList(ChatLieuDayDeoListRequest.builder()
                .tenChatLieuDayDeo(tenChatLieuDayDeo)
                .page(page)
                .size(size).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ChatLieuDayDeoCreateRequest request){
        return chatLieuDayDeoService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ChatLieuDayDeoUpdateRequest request){
        return chatLieuDayDeoService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return chatLieuDayDeoService.getById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteRequest deleteRequest) {
        return chatLieuDayDeoService.delete(deleteRequest);
    }
}
