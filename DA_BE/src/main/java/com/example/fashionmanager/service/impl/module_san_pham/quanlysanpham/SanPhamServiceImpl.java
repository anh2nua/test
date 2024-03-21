package com.example.fashionmanager.service.impl.module_san_pham.quanlysanpham;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.response.ChatLieuDayDeoResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response.DanhMucDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.Response.KieuKhoaResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.response.LoaiSanPhamResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response.ChiTietSanPhamDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response.SanPhamDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.response.SanPhamResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.response.SoNganResponse;
import com.example.fashionmanager.entity.*;
import com.example.fashionmanager.enums.HistoryMethod;
import com.example.fashionmanager.enums.TenBangEnum;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.*;
import com.example.fashionmanager.service.impl.module_san_pham.chatlieu.ChatLieuService;
import com.example.fashionmanager.service.impl.module_san_pham.danhmuc.DanhMucService;
import com.example.fashionmanager.service.impl.module_san_pham.hoatiet.HoaTietService;
import com.example.fashionmanager.service.impl.module_san_pham.kichthuoc.KichThuocService;
import com.example.fashionmanager.service.impl.module_san_pham.mausac.MauSacService;
import com.example.fashionmanager.service.impl.module_san_pham.thuonghieu.ThuongHieuService;
import com.example.fashionmanager.util.GenerateCodeUtils;
import com.example.fashionmanager.util.HistoryUtils;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    ChatLieuRepository chatLieuRepository;

    @Autowired
    ChatLieuDayDeoRepository chatLieuDayDeoRepository;

    @Autowired
    LoaiSanPhamRepository loaiSanPhamRepository;
    @Autowired
    MauSacRepository mauSacRepository;
    @Autowired
    DanhMucRepository danhMucRepository;

    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Autowired
    KichThuocRepository kichThuocRepository;

    @Autowired
    HoaTietRepository hoaTietRepository;
    @Autowired
    KieuKhoaRepository kieuKhoaRepository;

    @Autowired
    SoNganRepository soNganRepository;

    @Autowired
    SanPhamDanhMucRepository sanPhamDanhMucRepository;

    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    MauSacService mauSacService;
    @Autowired
    HoaTietService hoaTietService;

    @Autowired
    ChatLieuService chatLieuService;
    @Autowired
    KichThuocService kichThuocService;
    @Autowired
    ThuongHieuService thuongHieuService;
    @Autowired
    DanhMucService danhMucService;

    @Autowired
    GenerateCodeUtils generateCodeUtils;

    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    LichSutuongTacRepository lichSutuongTacRepository;

    @Autowired
    HistoryUtils historyUtils;


    @Override
    public ResponseEntity<?> getList(SanPhamListRequest sanPhamListRequest) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate"),
                new Sort.Order(Sort.Direction.DESC, "id")

        );
        Pageable pageable = PageRequest.of(sanPhamListRequest.getPage(), sanPhamListRequest.getSize(), sort);
        Specification<SanPhamEntity> sanPhamEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(sanPhamListRequest.getMaSanPham())) {
                predicates.add(criteriaBuilder.like(root.get("maSanPham"), "%" + sanPhamListRequest.getMaSanPham() + "%"));
            }
            if (StringUtils.isNotBlank(sanPhamListRequest.getTenSanPham())) {
                predicates.add(criteriaBuilder.like(root.get("tenSanPham"), "%" + sanPhamListRequest.getTenSanPham() + "%"));
            }
            if (sanPhamListRequest.getLoaiSanPhamId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("loaiSanPhamEntity").get("id"), sanPhamListRequest.getLoaiSanPhamId()));
            }
            if (sanPhamListRequest.getThuongHieuId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("thuongHieuEntity").get("id"), sanPhamListRequest.getThuongHieuId()));
            }
            if (sanPhamListRequest.getDanhMucIds() != null && !sanPhamListRequest.getDanhMucIds().isEmpty()) {
                root.join("sanPhamDanhMucEntities", JoinType.INNER);
                root.join("sanPhamDanhMucEntities").join("danhMucEntity", JoinType.INNER);
                predicates.add((root.join("sanPhamDanhMucEntities").join("danhMucEntity").get("id").in(sanPhamListRequest.getDanhMucIds())));
            }


            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));

        };
        Page<SanPhamEntity> sanPhamEntities = sanPhamRepository.findAll(sanPhamEntitySpecification, pageable);
        List<SanPhamResponse> sanPhamResponses = sanPhamEntities.stream().map(this::mappingByResponse).toList();
        ListReponseDto<SanPhamResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(sanPhamResponses);
        listReponseDto.setPageIndex(pageable.getPageNumber());
        listReponseDto.setHasNextPage(sanPhamEntities.hasNext());
        listReponseDto.setHasPreviousPage(sanPhamEntities.hasPrevious());
        listReponseDto.setPageCount(sanPhamEntities.getTotalPages());
        listReponseDto.setPageSize(sanPhamEntities.getSize());
        listReponseDto.setTotalItemCount(sanPhamEntities.getTotalElements());
        return new ResponseEntity<>(listReponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        SanPhamEntity sanPhamEntity = sanPhamRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm có id = " + id)));
        return new ResponseEntity<>(mappingResponseDetail(sanPhamEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(SanPhamCreateRequest sanPhamCreateRequest) {
        SanPhamEntity sanPhamEntity = mappingByCreateRequest(sanPhamCreateRequest);
        sanPhamRepository.save(sanPhamEntity);
        //tạo lịch sử tương tác
        historyUtils.createLichSuTuongTac(
                historyUtils.getNhanVienEntity(),
                HistoryMethod.CREATE,
                null,
                sanPhamEntity,
                TenBangEnum.SAN_PHAM,
                sanPhamCreateRequest.getMoTaTuongTac());
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> update(SanPhamUpdateRequest sanPhamUpdateRequest) {
        if (!sanPhamRepository.existsById(sanPhamUpdateRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "KHông tìm thấy sản phẩm có id = " + sanPhamUpdateRequest.getId()));
        }
        SanPhamEntity sanPhamEntity = mappingByUpdateRequest(sanPhamUpdateRequest);
        sanPhamRepository.save(sanPhamEntity);
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<SanPhamEntity> sanPhamEntities = sanPhamRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(sanPhamEntity -> sanPhamEntity.setDeleted(deleteRequest.getDeleted())).toList();
            sanPhamRepository.saveAll(sanPhamEntities);
        } else {
            SanPhamEntity sanPhamEntity = sanPhamRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            sanPhamEntity.setDeleted(deleteRequest.getDeleted());
            sanPhamRepository.save(sanPhamEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }


    @Override
    public SanPhamEntity mappingByCreateRequest(SanPhamCreateRequest sanPhamCreateRequest) {
        SanPhamEntity sanPhamEntity = new SanPhamEntity();
        //set giá trị thường
        sanPhamEntity.setMaSanPham(generateCodeUtils.generateCodeSanPham());
        sanPhamEntity.setPhuHopSuDung(sanPhamCreateRequest.getPhuHopSuDung());
        sanPhamEntity.setMoTa(sanPhamCreateRequest.getMoTa());

        //set giá trị bảng con 1
        sanPhamEntity.setChatLieuEntity(chatLieuRepository.findById(sanPhamCreateRequest.getChatLieuId()).orElse(null));
        sanPhamEntity.setLoaiSanPhamEntity(loaiSanPhamRepository.findById(sanPhamCreateRequest.getLoaiSanPhamId()).orElse(null));
        sanPhamEntity.setHoaTietEntity(hoaTietRepository.findById(sanPhamCreateRequest.getHoaTietId()).orElse(null));
        sanPhamEntity.setKichThuocEntity(kichThuocRepository.findById(sanPhamCreateRequest.getKichThuocId()).orElse(null));
        sanPhamEntity.setChatLieuDayDeoEntity(chatLieuDayDeoRepository.findById(sanPhamCreateRequest.getChatLieuDayDeoId()).orElse(null));
        sanPhamEntity.setKieuKhoaEntity(kieuKhoaRepository.findById(sanPhamCreateRequest.getKieuKhoaId()).orElse(null));
        sanPhamEntity.setThuongHieuEntity(thuongHieuRepository.findById(sanPhamCreateRequest.getThuongHieuId()).orElse(null));
        sanPhamEntity.setSoNganEntity(soNganRepository.findById(sanPhamCreateRequest.getSoNganId()).orElse(null));

        //set danh mục
        Set<SanPhamDanhMucEntity> sanPhamDanhMucEntities = danhMucRepository.findAllById(sanPhamCreateRequest.getDanhMucIds().stream().distinct().toList()).stream().map(danhMucEntity -> {
            return SanPhamDanhMucEntity.builder().sanPhamEntity(sanPhamEntity).danhMucEntity(danhMucEntity).build();
        }).collect(Collectors.toSet());
        sanPhamEntity.setSanPhamDanhMucEntities(sanPhamDanhMucEntities);


        //set chi tiết sản phẩm
        Set<ChiTietSanPhamEntity> chiTietSanPhamEntities = sanPhamCreateRequest.getChiTietSanPhamRequests().stream().map(chiTietSanPhamRequest -> {
            ChiTietSanPhamEntity chiTietSanPhamEntity = new ChiTietSanPhamEntity();
            chiTietSanPhamEntity.setSanPhamEntity(sanPhamEntity);
            chiTietSanPhamEntity.setGiaBanNiemYet(chiTietSanPhamRequest.getGiaBanNiemYet());
            chiTietSanPhamEntity.setHinhThucGiamGia(chiTietSanPhamRequest.getHinhThucGiamGia());
            chiTietSanPhamEntity.setGiaTriDuocGiam(chiTietSanPhamRequest.getGiaTriDuocGiam());
            chiTietSanPhamEntity.setGiaBanCuoiCung(chiTietSanPhamRequest.getGiaBanCuoiCung());
            if (chiTietSanPhamRequest.getMauSacId() != null) {
                chiTietSanPhamEntity.setMauSacEntity(mauSacRepository.findById(chiTietSanPhamRequest.getMauSacId()).orElse(null));
            }
            chiTietSanPhamEntity.setSoLuongBan(chiTietSanPhamRequest.getSoLuongBan());
            chiTietSanPhamEntity.setSoLuongTang(chiTietSanPhamRequest.getSoLuongTang());
            chiTietSanPhamEntity.setTrangThaiCTSP(chiTietSanPhamRequest.getTrangThaiCTSP());
            //Set hình ảnh
            //------------------------

            return chiTietSanPhamEntity;
        }).collect(Collectors.toSet());
        sanPhamEntity.setChiTietSanPhamEntities(chiTietSanPhamEntities);
        return sanPhamEntity;
    }

    @Override
    public SanPhamEntity mappingByUpdateRequest(SanPhamUpdateRequest sanPhamUpdateRequest) {
        //xóa thông tin cũ
        chiTietSanPhamRepository.deleteAllByIdSanPham(sanPhamUpdateRequest.getId());
        sanPhamDanhMucRepository.deleteAllByIdSanPham(sanPhamUpdateRequest.getId());

        SanPhamEntity sanPhamEntity = new SanPhamEntity();

        //set giá trị thường
        sanPhamEntity.setId(sanPhamUpdateRequest.getId());
        sanPhamEntity.setMaSanPham(sanPhamEntity.getMaSanPham());
        sanPhamEntity.setPhuHopSuDung(sanPhamUpdateRequest.getPhuHopSuDung());
        sanPhamEntity.setMoTa(sanPhamUpdateRequest.getMoTa());

        //set giá trị bảng con 1
        sanPhamEntity.setChatLieuEntity(chatLieuRepository.findById(sanPhamUpdateRequest.getChatLieuId()).orElse(null));
        sanPhamEntity.setLoaiSanPhamEntity(loaiSanPhamRepository.findById(sanPhamUpdateRequest.getLoaiSanPhamId()).orElse(null));
        sanPhamEntity.setHoaTietEntity(hoaTietRepository.findById(sanPhamUpdateRequest.getHoaTietId()).orElse(null));
        sanPhamEntity.setKichThuocEntity(kichThuocRepository.findById(sanPhamUpdateRequest.getKichThuocId()).orElse(null));
        sanPhamEntity.setChatLieuDayDeoEntity(chatLieuDayDeoRepository.findById(sanPhamUpdateRequest.getChatLieuDayDeoId()).orElse(null));
        sanPhamEntity.setKieuKhoaEntity(kieuKhoaRepository.findById(sanPhamUpdateRequest.getKieuKhoaId()).orElse(null));
        sanPhamEntity.setThuongHieuEntity(thuongHieuRepository.findById(sanPhamUpdateRequest.getThuongHieuId()).orElse(null));
        sanPhamEntity.setSoNganEntity(soNganRepository.findById(sanPhamUpdateRequest.getSoNganId()).orElse(null));

        //set danh mục
        Set<SanPhamDanhMucEntity> sanPhamDanhMucEntities = danhMucRepository.findAllById(sanPhamUpdateRequest.getDanhMucIds().stream().distinct().toList()).stream().map(danhMucEntity -> {
            return SanPhamDanhMucEntity.builder().sanPhamEntity(sanPhamEntity).danhMucEntity(danhMucEntity).build();
        }).collect(Collectors.toSet());
        sanPhamEntity.setSanPhamDanhMucEntities(sanPhamDanhMucEntities);

        //set chi tiết sản phẩm
        Set<ChiTietSanPhamEntity> chiTietSanPhamEntities = sanPhamUpdateRequest.getChiTietSanPhamRequests().stream().map(chiTietSanPhamRequest -> {
            ChiTietSanPhamEntity chiTietSanPhamEntity = new ChiTietSanPhamEntity();
            chiTietSanPhamEntity.setSanPhamEntity(sanPhamEntity);
            chiTietSanPhamEntity.setGiaBanNiemYet(chiTietSanPhamRequest.getGiaBanNiemYet());
            chiTietSanPhamEntity.setHinhThucGiamGia(chiTietSanPhamRequest.getHinhThucGiamGia());
            chiTietSanPhamEntity.setGiaTriDuocGiam(chiTietSanPhamRequest.getGiaTriDuocGiam());
            chiTietSanPhamEntity.setGiaBanCuoiCung(chiTietSanPhamRequest.getGiaBanCuoiCung());
            if (chiTietSanPhamRequest.getMauSacId() != null) {
                chiTietSanPhamEntity.setMauSacEntity(mauSacRepository.findById(chiTietSanPhamRequest.getMauSacId()).orElse(null));
            }
            chiTietSanPhamEntity.setSoLuongBan(chiTietSanPhamRequest.getSoLuongBan());
            chiTietSanPhamEntity.setSoLuongTang(chiTietSanPhamRequest.getSoLuongTang());
            chiTietSanPhamEntity.setTrangThaiCTSP(chiTietSanPhamRequest.getTrangThaiCTSP());
            //Set hình ảnh
            //------------------------

            return chiTietSanPhamEntity;
        }).collect(Collectors.toSet());
        sanPhamEntity.setChiTietSanPhamEntities(chiTietSanPhamEntities);
        return sanPhamEntity;
    }

    @Override
    public SanPhamResponse mappingByResponse(SanPhamEntity sanPhamEntity) {
        SanPhamResponse sanPhamResponse = new SanPhamResponse();
        sanPhamResponse.setId(sanPhamEntity.getId());
        sanPhamResponse.setMaSanPham(sanPhamEntity.getMaSanPham());
        sanPhamResponse.setTenSanPham(sanPhamEntity.getTenSanPham());
        sanPhamResponse.setTenLoaiSanPham(sanPhamEntity.getLoaiSanPhamEntity() == null ? null : sanPhamEntity.getLoaiSanPhamEntity().getTenLoai());
        if (sanPhamEntity.getChiTietSanPhamEntities() != null && !sanPhamEntity.getChiTietSanPhamEntities().isEmpty()) {
            ChiTietSanPhamEntity chiTietSanPhamEntity = sanPhamEntity.getChiTietSanPhamEntities().stream().toList().get(0);
            if (chiTietSanPhamEntity.getHinhAnhEntities() != null && !chiTietSanPhamEntity.getHinhAnhEntities().isEmpty()) {
                HinhAnhEntity hinhAnhEntity = chiTietSanPhamEntity.getHinhAnhEntities().stream().toList().get(0);
                sanPhamResponse.setAnhDaiDien(hinhAnhEntity.getUrl());
            }
        }
        return sanPhamResponse;
    }

    @Override
    public SanPhamDetailResponse mappingResponseDetail(SanPhamEntity sanPhamEntity) {
        SanPhamDetailResponse sanPhamDetailResponse = new SanPhamDetailResponse();
        sanPhamDetailResponse.setId(sanPhamEntity.getId());
        sanPhamDetailResponse.setMaSanPham(sanPhamEntity.getMaSanPham());
        sanPhamDetailResponse.setTenSanPham(sanPhamEntity.getTenSanPham());
        if (sanPhamEntity.getChatLieuDayDeoEntity() != null) {
            sanPhamDetailResponse.setChatLieuDayDeoResponse(ChatLieuDayDeoResponse.builder().build());
        }
        if (sanPhamEntity.getHoaTietEntity() != null) {
            sanPhamDetailResponse.setHoaTietResponse(hoaTietService.mappingByResponse(sanPhamEntity.getHoaTietEntity()));
        }
        if (sanPhamEntity.getKichThuocEntity() != null) {
            sanPhamDetailResponse.setKichThuocResponse(kichThuocService.mappingResponseDetail(sanPhamEntity.getKichThuocEntity()));
        }
        if (sanPhamEntity.getKieuKhoaEntity() != null) {
            sanPhamDetailResponse.setKieuKhoaResponse(KieuKhoaResponse.builder().id(sanPhamEntity.getKieuKhoaEntity().getId()).tenKieuKhoa(sanPhamEntity.getKieuKhoaEntity().getTenKieuKhoa()).build());
        }
        if (sanPhamEntity.getChatLieuEntity() != null) {
            sanPhamDetailResponse.setChatLieuResponse(chatLieuService.mappingByResponse(sanPhamEntity.getChatLieuEntity()));
        }
        if (sanPhamEntity.getSoNganEntity() != null) {
            sanPhamDetailResponse.setSoNganResponse(SoNganResponse.builder().id(sanPhamEntity.getSoNganEntity().getId()).tenSoNgan(sanPhamEntity.getSoNganEntity().getTenSoNgan()).build());
        }
        if (sanPhamEntity.getLoaiSanPhamEntity() != null) {
            sanPhamDetailResponse.setLoaiSanPhamResponse(LoaiSanPhamResponse.builder().id(sanPhamEntity.getLoaiSanPhamEntity().getId()).tenLoaiSanPham(sanPhamEntity.getLoaiSanPhamEntity().getTenLoai()).build());
        }
        if (sanPhamEntity.getLoaiSanPhamEntity() != null) {
            sanPhamDetailResponse.setThuongHieuReponse(thuongHieuService.mappingByResponse(sanPhamEntity.getThuongHieuEntity()));
        }
        sanPhamDetailResponse.setPhuHopSuDung(sanPhamEntity.getPhuHopSuDung());
        sanPhamDetailResponse.setMoTa(sanPhamEntity.getMoTa());
        if (!sanPhamEntity.getSanPhamDanhMucEntities().isEmpty()) {
            List<DanhMucDetailResponse> danhMucDetailResponses = sanPhamEntity.getSanPhamDanhMucEntities().stream().map(SanPhamDanhMucEntity::getDanhMucEntity).map(this.danhMucService::mappingResponseDetail).toList();
            sanPhamDetailResponse.setDanhMucApDung(danhMucDetailResponses);
        }
        if (!sanPhamEntity.getChiTietSanPhamEntities().isEmpty()) {
            List<ChiTietSanPhamDetailResponse> chiTietSanPhamDetailResponses = sanPhamEntity.getChiTietSanPhamEntities().stream().map(this::mappingChiTietSanPhamDetailResponse).toList();
            sanPhamDetailResponse.setChiTietSanPhamDetailResponses(chiTietSanPhamDetailResponses);
        }

        return sanPhamDetailResponse;
    }

    private ChiTietSanPhamDetailResponse mappingChiTietSanPhamDetailResponse(ChiTietSanPhamEntity chiTietSanPhamEntity) {
        ChiTietSanPhamDetailResponse chiTietSanPhamDetailResponse = new ChiTietSanPhamDetailResponse();
        chiTietSanPhamDetailResponse.setId(chiTietSanPhamEntity.getId());
        if (chiTietSanPhamEntity.getMauSacEntity() != null) {
            chiTietSanPhamDetailResponse.setMauSacResponse(mauSacService.mappingResponseDetail(chiTietSanPhamEntity.getMauSacEntity()));
        }
        chiTietSanPhamDetailResponse.setGiaBanNiemYet(chiTietSanPhamEntity.getGiaBanNiemYet());
        chiTietSanPhamDetailResponse.setHinhThucGiamGia(chiTietSanPhamEntity.getHinhThucGiamGia());
        chiTietSanPhamDetailResponse.setGiaBanCuoiCung(chiTietSanPhamEntity.getGiaBanCuoiCung());
        chiTietSanPhamDetailResponse.setSoLuongBan(chiTietSanPhamEntity.getSoLuongBan());
        chiTietSanPhamDetailResponse.setSoLuongTang(chiTietSanPhamEntity.getSoLuongTang());
        chiTietSanPhamDetailResponse.setTrangThaiCTSP(chiTietSanPhamEntity.getTrangThaiCTSP());
        if (!chiTietSanPhamEntity.getHinhAnhEntities().isEmpty()) {
            List<Long> hinhAnhIds = chiTietSanPhamEntity.getHinhAnhEntities().stream().map(HinhAnhEntity::getId).toList();
            chiTietSanPhamDetailResponse.setHinhAnhIds(hinhAnhIds);
        }
        return chiTietSanPhamDetailResponse;
    }
}
