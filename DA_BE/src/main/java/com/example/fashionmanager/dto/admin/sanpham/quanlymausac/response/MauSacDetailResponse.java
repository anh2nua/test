package com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class MauSacDetailResponse {
    private Long id;
    private String maMau;
    private String tenMau;
}
