package com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class DanhMucListRequest extends ListRequestDto {
    private String maDanhMuc;
    private String tenDanhMuc;
    private Long idDanhMucParent;
    private Boolean isParent;
}
