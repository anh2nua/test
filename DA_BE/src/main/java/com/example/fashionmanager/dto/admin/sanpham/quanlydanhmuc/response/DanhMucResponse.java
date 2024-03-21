package com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanhMucResponse {
    private Long id;
    private String maDanhMuc;
    private String tenDanhMuc;

    private Boolean isParent;
    private String tenDanhMucParent;
}
