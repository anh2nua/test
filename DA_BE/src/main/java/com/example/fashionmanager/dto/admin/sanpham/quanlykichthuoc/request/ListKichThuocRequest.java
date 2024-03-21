package com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListKichThuocRequest extends ListRequestDto {
   private Long kichCoId;

}
