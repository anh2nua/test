package com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateKichThuocRequest {
    private Integer chieuDai;
    private Integer chieuRong;
    private Integer chieuCao;
    private Long kichCoId;
}
