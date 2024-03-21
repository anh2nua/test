package com.example.fashionmanager.dto.admin.nhanvien.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienResponse {
    private Long id;
    private String tenNhanVien;
    private String soDienThoai;
    private Boolean gioiTinh;
    private String anhUrl;

}
