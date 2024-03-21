package com.example.fashionmanager.dto.admin.sanpham.quanlyhoatiet.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HoaTietDetailResponse {
    private Long id;
    private String tenHoaTiet;
    private  String moTa;
}
