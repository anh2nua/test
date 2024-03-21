package com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TinTucResponse {
    private Long id;
    private String title;
    private Date postingDate;
    private Date dropDate;
    private String image;
    private String introduction;
    private String content;
}
