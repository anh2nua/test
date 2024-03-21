package com.example.fashionmanager.dto.admin.nhanvien.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NhanVienUpdateRequest extends NhanVienCreateRequest {
    private Long id;

}
