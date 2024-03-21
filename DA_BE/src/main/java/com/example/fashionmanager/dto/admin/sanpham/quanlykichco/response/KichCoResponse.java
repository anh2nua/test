package com.example.fashionmanager.dto.admin.sanpham.quanlykichco.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KichCoResponse {
    private Long id;
    private String tenKichCo;
}
