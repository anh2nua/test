package com.example.fashionmanager.util;

import com.example.fashionmanager.entity.LichSuTuongTacEntity;
import com.example.fashionmanager.entity.NhanVienEntity;
import com.example.fashionmanager.enums.HistoryMethod;
import com.example.fashionmanager.enums.TenBangEnum;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.LichSutuongTacRepository;
import com.example.fashionmanager.repository.NhanVienRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HistoryUtils {
    @Autowired
    LichSutuongTacRepository lichSutuongTacRepository;
    @Autowired
    NhanVienRepository nhanVienRepository;

    public void createLichSuTuongTac(NhanVienEntity nhanVienEntity, HistoryMethod historyMethod,
                                     Object duLieuCu, Object duLieuMoi,TenBangEnum tenBangEnum, String moTa) {
        ObjectMapper objectMapper = new ObjectMapper();
        String duLieuCuString = null;
        String duLieuMoiString = null;
        try {
            duLieuCuString = Objects.isNull(duLieuCu) ? null : objectMapper.writeValueAsString(duLieuCu);
            duLieuMoiString = Objects.isNull(duLieuMoi) ? null : objectMapper.writeValueAsString(duLieuMoi);
        } catch (JsonProcessingException e) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
        LichSuTuongTacEntity lichSuTuongTacEntity = LichSuTuongTacEntity.builder()
                .nhanVienEntity(nhanVienEntity)
                .thaoTac(historyMethod)
                .moTa(moTa)
                .tenBang(tenBangEnum)
                .duLieuCu(duLieuCuString)
                .duLieuMoi(duLieuMoiString)
                .build();
        lichSutuongTacRepository.save(lichSuTuongTacEntity);
    }

    public NhanVienEntity getNhanVienEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return nhanVienRepository.findByUserName(username).orElseThrow(() ->
                new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND
                        , "Không tìm thấy nhân viên có user name = " + username)
                )
        );
    }
}
