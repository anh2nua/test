package com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class KickCoUpdateRequest extends KichCoCreateRequest {
    private Long id;
}
