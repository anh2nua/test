package com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TinTucUpdateRequest extends TinTucCreateRequest{
    private Long id;
}
