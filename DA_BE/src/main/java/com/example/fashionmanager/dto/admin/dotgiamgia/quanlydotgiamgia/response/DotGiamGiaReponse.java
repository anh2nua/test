package com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response;

import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.enums.LoaiUuDaiDDG;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class DotGiamGiaReponse {

    private Long id;
    private String tenDotgiamGia;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private LoaiUuDaiDDG loaiUuDaiDDG;

    private DotGiamGiaStatus dotGiamGiaStatus;
}
