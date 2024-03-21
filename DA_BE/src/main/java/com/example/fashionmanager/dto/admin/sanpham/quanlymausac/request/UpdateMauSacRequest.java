package com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request;

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
public class UpdateMauSacRequest extends CreateMauSacRequest{
    private Long id;
}
