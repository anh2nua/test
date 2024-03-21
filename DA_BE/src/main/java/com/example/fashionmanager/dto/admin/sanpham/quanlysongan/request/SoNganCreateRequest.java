package com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class SoNganCreateRequest {
    private String tenSoNgan;
}
