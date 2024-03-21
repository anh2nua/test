package com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanhMucDetailResponse {
    private Long id;
    private String maDanhMuc;
    private String tenDanhMuc;
    private DanhMucDetailResponse  danhMucParent;
    private Boolean isParent;

    private List<DanhMucResponse> danhMucResponses = new ArrayList<>();
}
