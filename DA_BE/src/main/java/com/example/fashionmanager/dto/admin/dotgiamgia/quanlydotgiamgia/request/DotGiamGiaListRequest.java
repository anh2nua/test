package com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request;

import com.example.fashionmanager.dto.ListRequestDto;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.enums.LoaiUuDaiDDG;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Setter
@Getter
@SuperBuilder
public class DotGiamGiaListRequest extends ListRequestDto {
    private String tenDotGiamGia;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private LoaiUuDaiDDG loaiUuDaiDDG;

    private DotGiamGiaStatus dotGiamGiaStatus;
}
