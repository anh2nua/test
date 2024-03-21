package com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DanhMucCreateRequest {
    private String maDanhMuc;
    private String tenDanhMuc;
    private Boolean isParent;
    private Long danhMucId;
    private List<Long> danhMucs;
}
