package com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TinTucCreateRequest {
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postingDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dropDate;
    private String image;
    private String introduction;
    private String content;
    private MultipartFile file;
}
