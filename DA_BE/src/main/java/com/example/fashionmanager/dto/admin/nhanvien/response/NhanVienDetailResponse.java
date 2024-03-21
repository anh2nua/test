package com.example.fashionmanager.dto.admin.nhanvien.response;

import com.example.fashionmanager.enums.RoleEnums;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienDetailResponse {
    private Long id;
    private String tenNhanVien;
    private String cccd;
    private String soDienThoai;
    private String diaChi;
    private Boolean gioiTinh;
    private String anhUrl;
    private String tenDangNhap;
    private String email;
    private LocalDate ngaySinh;
    private List<RoleEnums> chucVu;
}
