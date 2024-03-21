package com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request;

import com.example.fashionmanager.dto.ListRequestDto;
import com.example.fashionmanager.enums.SanPhamOrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class SanPhamListRequest extends ListRequestDto {
    private String maSanPham;
    private String tenSanPham;

    private List<Long> danhMucIds;
    private Long loaiSanPhamId;
    private Long thuongHieuId;
    private SanPhamOrderBy sanPhamOrderBy;

}
