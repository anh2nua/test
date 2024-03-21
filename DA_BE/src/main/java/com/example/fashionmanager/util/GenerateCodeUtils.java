package com.example.fashionmanager.util;

import com.example.fashionmanager.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateCodeUtils {
    @Autowired
    SanPhamRepository sanPhamRepository;

    public String generateCodeSanPham() {
        String code = "SP" + Math.abs((int) Math.floor(Math.random() * Integer.MAX_VALUE));
        if (sanPhamRepository.existsByMaSanPham(code)) {
            code = generateCodeSanPham();
        }
        return code;
    }
}
