package com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class KichThuocResponse {
    private Long id;
    private KichCoDetailResponse kichCoDetailResponse;
    private Integer chieuDai;
    private Integer chieuRong;
    private Integer chieuCao;
}
