package com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ThuongHieuReponse {
    private Long id;
    private String tenThuongHieu;
}
