package com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KichThuocDetailResponse {
    private Long id;
    private KichCoDetailResponse kichCoDetailResponse;
    private Integer chieuDai;
    private Integer chieuRong;
    private Integer chieuCao;
}
