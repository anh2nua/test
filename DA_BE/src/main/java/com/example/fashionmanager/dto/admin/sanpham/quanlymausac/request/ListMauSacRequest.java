package com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListMauSacRequest extends ListRequestDto {
    private String tenMau;
}
