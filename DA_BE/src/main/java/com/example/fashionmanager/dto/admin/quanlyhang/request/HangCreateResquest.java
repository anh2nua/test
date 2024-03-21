package com.example.fashionmanager.dto.admin.quanlyhang.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HangCreateResquest {
    @NotNull(message = "Không để trống rankCode")
    @NotBlank(message = "Không để trống rankCode")
    private String maHang;
    @NotNull(message = "Không để trống rankName")
    @NotBlank(message = "Không để trống rankName")
    private String tenHang;
}
