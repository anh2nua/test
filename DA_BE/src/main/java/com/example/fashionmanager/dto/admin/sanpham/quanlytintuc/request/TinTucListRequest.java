package com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class TinTucListRequest extends ListRequestDto {
    private Long id;
    private String title;
    private Date postingDate;
    private Date dropDate;
    private String image;
    private String introduction;
    private String content;
}
