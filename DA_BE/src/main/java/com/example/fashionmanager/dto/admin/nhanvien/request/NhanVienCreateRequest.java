package com.example.fashionmanager.dto.admin.nhanvien.request;

import com.example.fashionmanager.enums.RoleEnums;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NhanVienCreateRequest {
    private String tenNhanVien;
    private String cccd;
    private String soDienThoai;
    private String diaChi;
    private Boolean gioiTinh;
    private String anhUrl;
    private String tenDangNhap;
    private String email;
    private String matKhau;
    private LocalDate ngaySinh;
    private List<RoleEnums> chucVu;


}
