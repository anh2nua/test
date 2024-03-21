package com.example.fashionmanager.service.impl;

import com.example.fashionmanager.entity.*;
import com.example.fashionmanager.enums.*;
import com.example.fashionmanager.repository.*;
import com.example.fashionmanager.service.InitDatabaseService;
import com.example.fashionmanager.util.GenerateCodeUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InitDatabaseServiceImpl implements InitDatabaseService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ChatLieuRepository chatLieuRepository;
    private final HoaTietRepository hoaTietRepository;
    private final MauSacRepository mauSacRepository;
    private final DotGiamGiaRepository dotGiamGiaRepository;
    private final DanhMucRepository danhMucRepository;
    private final ThuongHieuRepository thuongHieuRepository;
    private final KichThuocRepository kichThuocRepository;
    private final KichCoRepository kichCoRepository;
    private final LoaiSanPhamRepository loaiSanPhamRepository;
    private final KieuKhoaRepository kieuKhoaRepository;
    private final SoNganRepository soNganRepository;
    private final ChatLieuDayDeoRepository chatLieuDayDeoRepository;
    private final SanPhamRepository sanPhamRepository;
    private final GenerateCodeUtils generateCodeUtils;
    private final NhanVienRepository nhanVienRepository;

    @Override
    @Transactional
    public void initData() {
        long count = userRepository.count();
        if (count > 0) {
            return;
        }
        Set<RoleEntity> roleEntities = new HashSet<>();
        RoleEntity roleEntityAdminProduct = RoleEntity.builder().roleName(RoleEnums.ROLE_ADMIN_PRODUCT).build();//nhan vien kho
        RoleEntity roleEntityAdminBill = RoleEntity.builder().roleName(RoleEnums.ROLE_ADMIN_BILL).build();//nhan vien lên đơn
        RoleEntity roleEntityAdminMaketing = RoleEntity.builder().roleName(RoleEnums.ROLE_ADMIN_MAKETING).build();//nhan vien sáng tạo nội dung
        RoleEntity roleEntitySuperAdmin = RoleEntity.builder().roleName(RoleEnums.ROLE_SUPER_ADMIN).build();// quan ly,chu cua hang
        RoleEntity roleEntityUser = RoleEntity.builder().roleName(RoleEnums.ROLE_USER).build();
        roleEntities.add(roleEntityAdminProduct);
        roleEntities.add(roleEntityAdminBill);
        roleEntities.add(roleEntityAdminMaketing);
        roleEntities.add(roleEntitySuperAdmin);
        roleEntities.add(roleEntityUser);
        roleRepository.saveAll(roleEntities);

        //Chủ của hàng
        UserEntity userEntitySuperAdmin = UserEntity.builder()
                .userName("admin@fashion")
                .email("admin.fashion@gmail.com")
                .password(passwordEncoder.encode("123@123"))
                .build();
        UserRoleEntity userRoleEntitySuperAdmin = UserRoleEntity.builder().roleEntity(roleEntitySuperAdmin).userEntity(userEntitySuperAdmin).build();
        Set<UserRoleEntity> userRoleEntitiesSuperAdmin = new HashSet<>();
        userRoleEntitiesSuperAdmin.add(userRoleEntitySuperAdmin);
        userEntitySuperAdmin.setUserRoleEntities(userRoleEntitiesSuperAdmin);
        NhanVienEntity superAdminNhanVienEntity = NhanVienEntity.builder()
                .userEntity(userEntitySuperAdmin)
                .anhUrl("pnd")
                .tenNhanVien("ADMIN")
                .soDienThoai("0886087054")
                .cccd("123435346454")
                .ngaySinh(LocalDate.of(2002,8,15))
                .gioiTinh(true)
                .diaChi("HN")
                .build();
        nhanVienRepository.save(superAdminNhanVienEntity);


        // Nhân viên kho
        UserEntity userEntityAdminProduct = UserEntity.builder()
                .userName("admin.product@fashion")
                .email("admin.product@gmail.com")
                .password(passwordEncoder.encode("123@123"))
                .build();
        UserRoleEntity userRoleEntityAdminProduct = UserRoleEntity.builder().roleEntity(roleEntityAdminProduct).userEntity(userEntityAdminProduct).build();
        Set<UserRoleEntity> userRoleEntitiesAdminProduct = new HashSet<>();
        userRoleEntitiesAdminProduct.add(userRoleEntityAdminProduct);
        userEntityAdminProduct.setUserRoleEntities(userRoleEntitiesAdminProduct);
        userRepository.save(userEntityAdminProduct);


        //Nhân viên lên đơn
        UserEntity userEntityAdminBill = UserEntity.builder()
                .userName("admin.bill@fashion")
                .email("admin.bill@gmail.com")
                .password(passwordEncoder.encode("123@123"))
                .build();
        UserRoleEntity userRoleEntityAdminBill = UserRoleEntity.builder().roleEntity(roleEntityAdminBill).userEntity(userEntityAdminBill).build();
        Set<UserRoleEntity> userRoleEntitiesAdminBill = new HashSet<>();
        userRoleEntitiesAdminBill.add(userRoleEntityAdminBill);
        userEntityAdminBill.setUserRoleEntities(userRoleEntitiesAdminBill);
        userRepository.save(userEntityAdminBill);


//        //Nhân viên su kien
        UserEntity userEntityAdminMaketing = UserEntity.builder()
                .userName("admin.maketing@fashion")
                .email("admin.maketing@gmail.com")
                .password(passwordEncoder.encode("123@123"))
                .build();
        UserRoleEntity userRoleEntityAdminMaketing = UserRoleEntity.builder().roleEntity(roleEntityAdminMaketing).userEntity(userEntityAdminMaketing).build();
        Set<UserRoleEntity> userRoleEntitiesAdminMaketing = new HashSet<>();
        userRoleEntitiesAdminMaketing.add(userRoleEntityAdminMaketing);
        userEntityAdminMaketing.setUserRoleEntities(userRoleEntitiesAdminMaketing);
        userRepository.save(userEntitySuperAdmin);

    }

    @Override
    @Transactional
    public void initDanhMuc() {
        Set<DanhMucEntity> danhMucEntities = new HashSet<>();
        DanhMucEntity danhMucEntity5 = DanhMucEntity.builder()
                .maDanhMuc("DM05")
                .tenDanhMuc("Túi loại lớn")
                .isParent(true)
                .build();
        DanhMucEntity danhMucEntity6 = DanhMucEntity.builder()
                .maDanhMuc("DM06")
                .tenDanhMuc("Túi loại nhỏ")
                .isParent(true)
                .build();
        danhMucEntity5 = danhMucRepository.save(danhMucEntity5);
        danhMucEntity6 = danhMucRepository.save(danhMucEntity6);
        DanhMucEntity danhMucEntity1 = DanhMucEntity.builder()
                .maDanhMuc("DM01")
                .tenDanhMuc("Túi da")
                .danhMucEntity(danhMucEntity5)
                .isParent(false)
                .build();
        DanhMucEntity danhMucEntity2 = DanhMucEntity.builder()
                .maDanhMuc("DM02")
                .tenDanhMuc("Túi thể thao")
                .danhMucEntity(danhMucEntity5)
                .isParent(false)
                .build();
        DanhMucEntity danhMucEntity3 = DanhMucEntity.builder()
                .maDanhMuc("DM03")
                .tenDanhMuc("Túi đi đã ngoại")
                .danhMucEntity(danhMucEntity6)
                .isParent(false)
                .build();
        DanhMucEntity danhMucEntity4 = DanhMucEntity.builder()
                .maDanhMuc("DM04")
                .tenDanhMuc("Túi công sở")
                .danhMucEntity(danhMucEntity6)
                .isParent(false)
                .build();
        danhMucEntities.add(danhMucEntity1);
        danhMucEntities.add(danhMucEntity2);
        danhMucEntities.add(danhMucEntity3);
        danhMucEntities.add(danhMucEntity4);
        danhMucRepository.saveAll(danhMucEntities);
    }

    @Override
    public void initThuongHieu() {
        Set<ThuongHieuEntity> thuongHieuEntities = new HashSet<>();
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("GUCCI").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("Coach").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("MLB Korea").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("Michael Kors").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("JW Anderson").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("Christian Dior").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("Chanel").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("Louis Vuitton").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("Prada").build());
        thuongHieuEntities.add(ThuongHieuEntity.builder().tenThuongHieu("Charles & Keith").build());
        thuongHieuRepository.saveAll(thuongHieuEntities);
    }

    @Override
    public void initChatLieu() {
        Set<ChatLieuEntity> chatLieuEntities = new HashSet<>();
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Laine (Wool)")
                .moTa("""
                        Laine là loại chất liệu phổ biến nhất cho áo suit. Nó có nhiều biến thể, bao gồm laine worsted 
                        (mềm, mịn), laine tweed (dày, có kết cấu), và laine flannel (được làm từ sợi mỏng và có cảm nhận
                         mịn). Laine thích hợp cho mọi mùa và có thể tạo ra các bộ suit cổ điển hoặc hiện đại.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Linen")
                .moTa("""
                        Là một loại chất liệu tự nhiên, linen thích hợp cho mùa hè vì nó thoáng mát và hút ẩm tốt. 
                        Tuy nhiên, linen có thể nhăn và nhàu, nên bạn cần phải sử dụng nó một cách cẩn thận.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Cotton")
                .moTa("""
                        Cotton là một chất liệu phổ biến cho áo suit mùa hè. Nó thoáng mát, nhẹ, và dễ chăm sóc. 
                        Cotton có thể có nhiều biến thể, bao gồm chino (mịn, không sáng bóng) và khaki (được xử lý để 
                        sáng bóng hơn).                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Silk (Lụa)")
                .moTa("""
                        Lụa thường được sử dụng cho áo suit cổ điển và trong các bữa tiệc hoặc dịp đặc biệt khác. 
                        Nó có cảm nhận mịn và sáng bóng, tạo ra một vẻ ngoại hình sang trọng.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Mohair")
                .moTa("""
                        Mohair là một loại sợi tạo từ sợi lông của dê Angora. Chất liệu này thường sáng bóng và bóng 
                        loáng, và thích hợp cho bộ suit dành cho mùa hè hoặc các sự kiện quan trọng.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Velvet (Nhung)")
                .moTa("""
                        Velvet là một loại vải mịn và mềm, thường được sử dụng cho áo suit cho các sự kiện hoặc trong 
                        mùa đông. Nó có cảm nhận mượt mà và sang trọng.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Tweed")
                .moTa("""
                        Tweed là loại vải dày, chứa nhiều kết cấu và thường được sử dụng cho áo suit cổ điển hoặc áo 
                        blazer. Nó thích hợp cho mùa đông và tạo ra vẻ ngoại hình lịch lãm.                                      
                        """)
                .build());
        chatLieuRepository.saveAll(chatLieuEntities);
    }

    @Override
    public void initSanPham() {
        Set<SanPhamEntity> sanPhamEntities = new HashSet<>();

        SanPhamEntity sanPhamEntity = SanPhamEntity.builder()
                .maSanPham(generateCodeUtils.generateCodeSanPham())
                .tenSanPham("Túi layer họa tiết ngựa vằn")
                .moTa("Túi layer họa tiết ngựa vằn có mô tả...")
                .phuHopSuDung("Đi làm, đi chơi")
                .chatLieuDayDeoEntity(chatLieuDayDeoRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .chatLieuEntity(chatLieuRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .hoaTietEntity(hoaTietRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .kichThuocEntity(kichThuocRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .kieuKhoaEntity(kieuKhoaRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .loaiSanPhamEntity(loaiSanPhamRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .soNganEntity(soNganRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .thuongHieuEntity(thuongHieuRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .build();

        sanPhamEntity.setSanPhamDanhMucEntities(danhMucRepository.findAllByIsParentAndDeletedIsFalse(false)
                .stream()
                .map(danhMucEntity ->
                        SanPhamDanhMucEntity.builder()
                                .sanPhamEntity(sanPhamEntity)
                                .danhMucEntity(danhMucEntity)
                                .build()).collect(Collectors.toSet()));

        Set<ChiTietSanPhamEntity> chiTietSanPhamEntities = new HashSet<>();
        ChiTietSanPhamEntity chiTietSanPhamEntity = ChiTietSanPhamEntity.builder()
                .sanPhamEntity(sanPhamEntity)
                .mauSacEntity(mauSacRepository.findFirstByActiveIsTrueAndDeletedIsFalse().orElse(null))
                .giaBanNiemYet(new BigDecimal(89000000))
                .hinhThucGiamGia(KieuGiamGia.NONE)
                .giaTriDuocGiam(new BigDecimal(BigInteger.ZERO))
                .giaBanCuoiCung(new BigDecimal(89000000))
                .soLuongBan(9999999)
                .soLuongTang(9999999)
                .trangThaiCTSP(TrangThaiCTSPEnums.CON_HANG)
                .build();
        chiTietSanPhamEntities.add(chiTietSanPhamEntity);
        sanPhamEntity.setChiTietSanPhamEntities(chiTietSanPhamEntities);
        sanPhamEntities.add(sanPhamEntity);
        sanPhamRepository.saveAll(sanPhamEntities);
    }

    @Override
    public void initMauSac() {
        Set<MauSacEntity> mauSacEntities = new HashSet<>();
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#000000")
                .tenMau("Màu Đen (Black)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#808080")
                .tenMau("Màu Xám (Gray)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#000080")
                .tenMau("Màu Xanh Navy (Navy Blue)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#0047AB")
                .tenMau("Màu Xanh Cobalt (Cobalt Blue)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#964B00")
                .tenMau("Màu Nâu (Brown)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#36454F")
                .tenMau("Màu Xám Charcoal (Charcoal Gray)")
                .build());
        mauSacRepository.saveAll(mauSacEntities);
    }

    @Override
    public void initKichThuoc() {
        Set<KichThuocEntity> kichThuocEntities = new HashSet<>();
        KichCoEntity kichCoEntity = kichCoRepository.findById(1L).orElse(null);
        kichThuocEntities.add(KichThuocEntity.builder()
                .kichCoEntity(kichCoEntity)
                .chieuCao(12)
                .chieuDai(18)
                .chieuRong(8)
                .build());
        kichThuocEntities.add(KichThuocEntity.builder()
                .kichCoEntity(kichCoEntity)
                .chieuCao(12)
                .chieuDai(18)
                .chieuRong(8)
                .build());
        kichThuocEntities.add(KichThuocEntity.builder()
                .kichCoEntity(kichCoEntity)
                .chieuCao(12)
                .chieuDai(18)
                .chieuRong(8)
                .build());
        kichThuocEntities.add(KichThuocEntity.builder()
                .kichCoEntity(kichCoEntity)
                .chieuCao(12)
                .chieuDai(18)
                .chieuRong(8)
                .build());
        kichThuocEntities.add(KichThuocEntity.builder()
                .kichCoEntity(kichCoEntity)
                .chieuCao(12)
                .chieuDai(18)
                .chieuRong(8)
                .build());
        kichThuocRepository.saveAll(kichThuocEntities);
    }

    @Override
    public void initKichCo() {
        Set<KichCoEntity> kichCoEntities = new HashSet<>();
        kichCoEntities.add(KichCoEntity.builder().tenKichCo("Rất nhỏ").build());
        kichCoEntities.add(KichCoEntity.builder().tenKichCo("Nhỏ").build());
        kichCoEntities.add(KichCoEntity.builder().tenKichCo("Trung bình").build());
        kichCoEntities.add(KichCoEntity.builder().tenKichCo("Lớn").build());
        kichCoEntities.add(KichCoEntity.builder().tenKichCo("Rất lớn").build());
        kichCoRepository.saveAll(kichCoEntities);
    }


    @Override
    public void initBinhLuan() {

    }


    @Override
    public void initHoaTiet() {
        Set<HoaTietEntity> hoaTietEntities = new HashSet<>();
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Solid Color(Màu đơn)")
                .moTa("""
                        Áo suit một màu là một trong những lựa chọn phổ biến nhất.
                        Áo suit này có màu đơn và không có họa tiết hoặc hoa văn nào.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Herringbone")
                .moTa("""
                        Họa tiết herringbone là một loại mô hình zigzag tạo ra từ việc kết hợp các 
                        dải màu tương phản nhau. Điều này tạo ra một mô hình hình xương cá trên áo.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Pinstripe (Vạt ngang nhỏ)")
                .moTa("""
                        Pinstripe là một họa tiết được tạo ra bằng các dải ngang nhỏ và 
                        thường có khoảng cách đều đặn. Nó thường tạo ra một diện mạo chuyên nghiệp và 
                        thích hợp cho môi trường công sở.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Check (Kẻ ô)")
                .moTa("""
                        Họa tiết kẻ ô là một loại họa tiết có các dải ngang và dọc tạo thành các ô vuông 
                        nhỏ hoặc lớn. Có nhiều loại kẻ ô khác nhau, bao gồm kẻ ô nhỏ (micro check), kẻ ô lớn 
                        (windowpane check), và nhiều họa tiết kẻ khác.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Houndstooth (Họa tiết chó săn)")
                .moTa("""
                        Houndstooth là một họa tiết thường có dạng ô vuông nhỏ
                         tạo nên một mô hình sọc xéo hoặc zigzag. Nó thường xuất hiện trong hai màu tương phản.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Bird's Eye")
                .moTa("""
                        Họa tiết bird's eye tạo ra một mô hình nhỏ, 
                        giống như các điểm đen hoặc màu tương tự trên nền áo.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Glen Plaid (Kẻ sọc nhỏ)")
                .moTa("""
                        Họa tiết glen plaid có các dải sọc mỏng xen kẽ với các dải sọc khác tạo
                         ra một họa tiết phức tạp. Nó thường xuất hiện trong các tông màu tối.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Prince of Wales Check (Kẻ hoàng gia xứ Wales)")
                .moTa("""
                        Họa tiết này tạo ra một mô hình lớn với các dải sọc và kẻ sọc nhỏ hơn xen kẽ. Nó thường có màu
                         trung tính và thanh lịch.
                        """)
                .build());
        hoaTietRepository.saveAll(hoaTietEntities);
    }


    @Override
    public void initDotGiamGia() {
        List<DotGiamGiaEntity> dotGiamGiaEntities = new ArrayList<>();
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện khai trương")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2023, 11, 20))
                .ngayKetThuc(LocalDate.of(2023, 11, 25))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(KieuGiamGia.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện mùa thu")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2023, 7, 20))
                .ngayKetThuc(LocalDate.of(2023, 7, 25))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(KieuGiamGia.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện mùa đông")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2023, 11, 29))
                .ngayKetThuc(LocalDate.of(2023, 12, 31))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(KieuGiamGia.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện tết")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2024, 2, 10))
                .ngayKetThuc(LocalDate.of(2024, 2, 21))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(KieuGiamGia.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaRepository.saveAll(dotGiamGiaEntities);
    }

    @Override
    public void initLoaiSanPham() {
        Set<LoaiSanPhamEntity> loaiSanPhamEntities = new HashSet<>();
        loaiSanPhamEntities.add(LoaiSanPhamEntity.builder().tenLoai("Túi xách").build());
        loaiSanPhamEntities.add(LoaiSanPhamEntity.builder().tenLoai("Túi đeo chéo").build());
        loaiSanPhamEntities.add(LoaiSanPhamEntity.builder().tenLoai("Túi thời trang").build());
        loaiSanPhamRepository.saveAll(loaiSanPhamEntities);
    }

    @Override
    public void initSoNgan() {
        Set<SoNganEntity> soNganEntities = new HashSet<>();
        soNganEntities.add(SoNganEntity.builder().tenSoNgan("3 ngăn nhỏ 1 ngăn lớn").build());
        soNganEntities.add(SoNganEntity.builder().tenSoNgan("3 ngăn nhỏ 2 ngăn lớn").build());
        soNganEntities.add(SoNganEntity.builder().tenSoNgan("2 ngăn nhỏ 1 ngăn lớn").build());
        soNganEntities.add(SoNganEntity.builder().tenSoNgan("2 ngăn nhỏ 2 ngăn lớn").build());
        soNganEntities.add(SoNganEntity.builder().tenSoNgan("2 ngăn nhỏ 4 ngăn lớn").build());
        soNganRepository.saveAll(soNganEntities);
    }

    @Override
    public void initChatLieuDayDeo() {
        Set<ChatLieuDayDeoEntity> chatLieuDayDeoEntities = new HashSet<>();
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây da tổng hợp (Synthetic Leather)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo vải (Fabric Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo da thật (Genuine Leather Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo dù (Nylon Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo kim loại (Metal Chain Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo PVC (PVC Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo bản (Wide Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo thun (Elastic Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo dây paracord (Paracord Strap)").build());
        chatLieuDayDeoEntities.add(ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo("Dây đeo da đính kèm (Leather Trimmed Strap)").build());
        chatLieuDayDeoRepository.saveAll(chatLieuDayDeoEntities);
    }

    @Override
    public void initKieuKhoa() {
    }


}
