package com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class KichCoListRequest extends ListRequestDto {
    private String tenKichCo;
}
