package com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateKichThuocRequest extends CreateKichThuocRequest{
    private Long id;
}
