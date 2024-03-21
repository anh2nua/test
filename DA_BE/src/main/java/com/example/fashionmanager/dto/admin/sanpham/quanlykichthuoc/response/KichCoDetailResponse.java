package com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KichCoDetailResponse {
    private Long id;
    private String tenKichCo;
}
