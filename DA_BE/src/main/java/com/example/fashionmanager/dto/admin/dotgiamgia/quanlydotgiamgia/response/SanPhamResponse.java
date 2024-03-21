package com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response;

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
public class SanPhamResponse {
    private Long id;

    private String tenSanPham;
}
