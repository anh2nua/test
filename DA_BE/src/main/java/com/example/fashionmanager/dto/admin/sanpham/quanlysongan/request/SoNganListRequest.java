package com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SoNganListRequest extends ListRequestDto {
    private String tenSoNgan;
}
