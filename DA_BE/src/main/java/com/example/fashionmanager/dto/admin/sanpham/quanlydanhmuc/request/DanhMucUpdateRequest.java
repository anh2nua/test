package com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DanhMucUpdateRequest extends DanhMucCreateRequest {
    private Long id;
}
