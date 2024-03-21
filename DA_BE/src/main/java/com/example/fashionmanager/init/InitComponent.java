package com.example.fashionmanager.init;

import com.example.fashionmanager.repository.*;
import com.example.fashionmanager.repository.ChatLieuRepository;
import com.example.fashionmanager.repository.DotGiamGiaRepository;
import com.example.fashionmanager.repository.HoaTietRepository;
import com.example.fashionmanager.repository.MauSacRepository;
import com.example.fashionmanager.repository.SanPhamRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.service.InitDatabaseService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitComponent {
    @Value("${fashion.app.init.database}")
    private boolean isInitDatabase;
    private final UserRepository userRepository;
    private final ChatLieuRepository chatLieuRepository;

    private final HoaTietRepository hoaTietRepository;

    private final SanPhamRepository sanPhamRepository;

    private final DotGiamGiaRepository dotGiamGiaRepository;

    private final InitDatabaseService initDatabaseService;
    private final MauSacRepository mauSacRepository;
    private final DanhMucRepository danhMucRepository;
    private final ThuongHieuRepository thuongHieuRepository;
    private final KichThuocRepository kichThuocRepository;
    private final KichCoRepository kichCoRepository;
    private final KieuKhoaRepository kieuKhoaRepository;
    private final SoNganRepository soNganRepository;
    private final LoaiSanPhamRepository loaiSanPhamRepository;
    private final ChatLieuDayDeoRepository chatLieuDayDeoRepository;


    @PostConstruct
    private void initDatabase() {
        if (!isInitDatabase) {
            return;
        }
        if (userRepository.count() == 0) {
            initDatabaseService.initData();
        }
        if (chatLieuRepository.count() == 0) {
            initDatabaseService.initChatLieu();
        }
        if (hoaTietRepository.count() == 0) {
            initDatabaseService.initHoaTiet();
        }
        if (dotGiamGiaRepository.count() == 0) {
            initDatabaseService.initDotGiamGia();
        }

        if (mauSacRepository.count() == 0) {
            initDatabaseService.initMauSac();
        }

        if (danhMucRepository.count() == 0) {
            initDatabaseService.initDanhMuc();
        }

        if (thuongHieuRepository.count() == 0) {
            initDatabaseService.initThuongHieu();
        }
        if (kichCoRepository.count() == 0) {
            initDatabaseService.initKichCo();
        }
        if (kichThuocRepository.count() == 0) {
            initDatabaseService.initKichThuoc();
        }
        if (kieuKhoaRepository.count() == 0) {
            initDatabaseService.initKieuKhoa();
        }
        if (soNganRepository.count() == 0) {
            initDatabaseService.initSoNgan();
        }
        if (loaiSanPhamRepository.count() == 0) {
            initDatabaseService.initLoaiSanPham();
        }
        if (chatLieuDayDeoRepository.count() == 0) {
            initDatabaseService.initChatLieuDayDeo();
        }
        if (sanPhamRepository.count() == 0) {
            initDatabaseService.initSanPham();
        }
    }
}
