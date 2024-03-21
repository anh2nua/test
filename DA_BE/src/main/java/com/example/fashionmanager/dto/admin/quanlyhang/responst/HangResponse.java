package com.example.fashionmanager.dto.admin.quanlyhang.responst;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HangResponse {
    private Long id;
    private String maHang;
    private String tenHang;
}
