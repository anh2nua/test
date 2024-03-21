package com.example.fashionmanager.dto.admin.sanpham.quanlyhoatiet.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListHoaTietRequest extends ListRequestDto {
    private String tenHoaTiet;
}
