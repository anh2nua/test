package com.example.fashionmanager.dto.shop.binhluan.request;

import com.example.fashionmanager.enums.TrangThaiBinhLuanEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApproveRequest {
    private Long id;
    private TrangThaiBinhLuanEnum trangThaiBinhLuan;
}
