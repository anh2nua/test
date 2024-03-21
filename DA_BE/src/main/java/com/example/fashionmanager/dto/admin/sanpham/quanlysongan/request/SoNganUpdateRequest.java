package com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SoNganUpdateRequest extends SoNganCreateRequest{
private Long id;
}
