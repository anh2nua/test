package com.example.fashionmanager.service.impl.module_san_pham.dot_giam_gia;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaCreateRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaListRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaUpdateRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response.DotGiamGiaReponse;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response.DotGiamGiaResponseDetail;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response.SanPhamApDungResponse;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response.SanPhamResponse;
import com.example.fashionmanager.entity.DotGiamGiaEntity;
import com.example.fashionmanager.entity.SanPhamApDungDGGEntity;
import com.example.fashionmanager.entity.SanPhamEntity;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.DotGiamGiaRepository;
import com.example.fashionmanager.repository.SanPhamApDungDDGRepository;
import com.example.fashionmanager.repository.SanPhamRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DotgiamGiaServiceImpl implements DotGiamGiaService {
    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository;
    @Autowired
    private SanPhamApDungDDGRepository sanPhamApDungDDGRepository;
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public ResponseEntity<?> getList(DotGiamGiaListRequest dotGiamGiaListRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(dotGiamGiaListRequest.getPage(), dotGiamGiaListRequest.getSize(), sort);
        Specification<DotGiamGiaEntity> dotGiamGiaEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(dotGiamGiaListRequest.getTenDotGiamGia())) {
                predicates.add(criteriaBuilder.like(root.get("tenDotGiamGia"), "%" + dotGiamGiaListRequest.getTenDotGiamGia() + "%"));
            }
            if (dotGiamGiaListRequest.getDotGiamGiaStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dotGiamGiaStatus"), dotGiamGiaListRequest.getDotGiamGiaStatus()));
            }
            if (dotGiamGiaListRequest.getNgayBatDau() != null && dotGiamGiaListRequest.getNgayKetThuc() != null) {
                predicates.add(criteriaBuilder.between(root.get("ngayBatDau"), dotGiamGiaListRequest.getNgayBatDau(), dotGiamGiaListRequest.getNgayKetThuc()));
                predicates.add(criteriaBuilder.between(root.get("ngayKetThuc"), dotGiamGiaListRequest.getNgayBatDau(), dotGiamGiaListRequest.getNgayKetThuc()));
            }
            if (dotGiamGiaListRequest.getLoaiUuDaiDDG() != null) {
                predicates.add(criteriaBuilder.equal(root.get("loaiUuDaiDDG"), dotGiamGiaListRequest.getLoaiUuDaiDDG()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<DotGiamGiaEntity> dotGiamGiaEntities = dotGiamGiaRepository.findAll(dotGiamGiaEntitySpecification, pageable);
        List<DotGiamGiaReponse> dotGiamGiaReponses = dotGiamGiaEntities.stream().map(dotGiamGiaEntity -> mappingByResponse(dotGiamGiaEntity)).toList();

        ListReponseDto<DotGiamGiaReponse> listReponseDto = new ListReponseDto<DotGiamGiaReponse>();
        listReponseDto.setItems(dotGiamGiaReponses);
        listReponseDto.setHasNextPage(dotGiamGiaEntities.hasNext());
        listReponseDto.setHasPreviousPage(dotGiamGiaEntities.hasPrevious());
        listReponseDto.setPageCount(dotGiamGiaEntities.getTotalPages());
        listReponseDto.setPageSize(dotGiamGiaEntities.getSize());
        listReponseDto.setTotalItemCount(dotGiamGiaEntities.getTotalElements());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        DotGiamGiaEntity dotGiamGiaEntity = dotGiamGiaRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND, "không tìm thấy đợt giảm giá có id = " + id
                )
        ));
        return ResponseEntity.ok(mappingResponseDetail(dotGiamGiaEntity));
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        if (dotGiamGiaRepository.existsByDotGiamGiaDangHoatDong(DotGiamGiaStatus.HOAT_DONG, dotGiamGiaCreateRequest.getNgayKetThuc(), null) ||
                dotGiamGiaRepository.existsByDotGiamGiaDangHoatDong(DotGiamGiaStatus.HOAT_DONG, dotGiamGiaCreateRequest.getNgayKetThuc(), null)
        ) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST, "Ngày bắt đầu hoặc ngày kết thúc đang nằm trong chuỗi ngày hoạt động của 1 sự kiện khác"
                    )
            );
        }
        DotGiamGiaEntity dotGiamGiaEntity = new DotGiamGiaEntity();
        dotGiamGiaEntity.setTenDotGiamGia(dotGiamGiaCreateRequest.getTenDotGiamGia());
        if (dotGiamGiaCreateRequest.getNgayBatDau().isAfter(dotGiamGiaCreateRequest.getNgayKetThuc())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST, "Ngày bắt đầu không được sau ngày kết thúc"
                    )
            );
        }
        dotGiamGiaEntity.setNgayBatDau(dotGiamGiaCreateRequest.getNgayBatDau());
        dotGiamGiaEntity.setNgayKetThuc(dotGiamGiaCreateRequest.getNgayKetThuc());
        dotGiamGiaEntity.setLoaiUuDaiDDG(dotGiamGiaCreateRequest.getLoaiUuDaiDDG());
        switch (dotGiamGiaCreateRequest.getLoaiUuDaiDDG()) {
            case DANH_MUC -> {
                dotGiamGiaEntity = loaiUuDaiDanhMuc(dotGiamGiaEntity, dotGiamGiaCreateRequest);
                break;
            }
            case HOA_DON -> {
                dotGiamGiaEntity = loaiUuDaiHoaDon(dotGiamGiaEntity, dotGiamGiaCreateRequest);
                break;
            }
            case SAN_PHAM -> {
                dotGiamGiaEntity = loaiUuDaiSanPham(dotGiamGiaEntity, dotGiamGiaCreateRequest);
                break;
            }
        }
        dotGiamGiaEntity.setDotGiamGiaStatus(dotGiamGiaCreateRequest.getDotGiamGiaStatus());
        dotGiamGiaRepository.save(dotGiamGiaEntity);
        return ResponseEntity.ok("CREATED");
    }

    private DotGiamGiaEntity loaiUuDaiDanhMuc(DotGiamGiaEntity dotGiamGiaEntity, DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        return dotGiamGiaEntity;
    }

    private DotGiamGiaEntity loaiUuDaiHoaDon(DotGiamGiaEntity dotGiamGiaEntity, DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        dotGiamGiaEntity.setSoTienHoaDonYeuCau(dotGiamGiaCreateRequest.getSoTienHoaDonYeuCau());
        dotGiamGiaEntity.setLoaiGiamGiaHD(dotGiamGiaCreateRequest.getLoaiGiamGiaHD());
        switch (dotGiamGiaCreateRequest.getLoaiGiamGiaHD()) {
            case AMOUNT -> {
                dotGiamGiaEntity.setGiaTriGiamHD(dotGiamGiaCreateRequest.getGiaTriGiamHD());
                break;
            }
            case PERCENT -> {
                int giaTriGiam = Integer.parseInt(dotGiamGiaCreateRequest.getGiaTriGiamHD().toString());
                if (giaTriGiam > 100 || giaTriGiam < 0) {
                    throw new FashionManagerException(
                            new ErrorResponse(
                                    HttpStatus.BAD_REQUEST, "Giá trị giảm phải trong khoảng từ 0% -> 100%"
                            )
                    );
                }
                dotGiamGiaEntity.setGiaTriGiamHD(dotGiamGiaCreateRequest.getGiaTriGiamHD());
                break;
            }
            case NONE -> {
                break;
            }
        }
        return dotGiamGiaEntity;
    }

    private DotGiamGiaEntity loaiUuDaiSanPham(DotGiamGiaEntity dotGiamGiaEntity, DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        if (dotGiamGiaCreateRequest.getSanPhamApDungDGGs() != null && !dotGiamGiaCreateRequest.getSanPhamApDungDGGs().isEmpty()) {
            Set<SanPhamApDungDGGEntity> sanPhamApDungDGGEntities = dotGiamGiaCreateRequest.getSanPhamApDungDGGs().stream().map(x -> {
                SanPhamApDungDGGEntity sanPhamApDungDGGEntity = new SanPhamApDungDGGEntity();
                SanPhamEntity sanPhamEntity = sanPhamRepository.findById(x.getIdSanPham()).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND, "không tìm thấy id sản phẩm có id = " + x.getIdSanPham()
                        )
                ));
                sanPhamApDungDGGEntity.setSanPhamEntity(sanPhamEntity);
                sanPhamApDungDGGEntity.setLoaiUuDai(x.getLoaiGiamGia());
                switch (x.getLoaiGiamGia()) {
                    case AMOUNT -> {
                        sanPhamApDungDGGEntity.setGiaTriDuocGiam(x.getGiaTriDuocGiam());
                        break;
                    }
                    case PERCENT -> {
                        int giaTriGiam = Integer.parseInt(x.getGiaTriDuocGiam().toString());
                        if (giaTriGiam > 100 || giaTriGiam < 0) {
                            throw new FashionManagerException(
                                    new ErrorResponse(
                                            HttpStatus.BAD_REQUEST, "Giá trị giảm phải trong khoảng từ 0% -> 100%"
                                    )
                            );
                        }
                        sanPhamApDungDGGEntity.setGiaTriDuocGiam(x.getGiaTriDuocGiam());
                        break;
                    }
                    case NONE -> {
                        break;
                    }
                }
                return sanPhamApDungDGGEntity;
            }).collect(Collectors.toSet());
            dotGiamGiaEntity.setSanPhamApDungDGGEntities(sanPhamApDungDGGEntities);
        }
        return dotGiamGiaEntity;
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(DotGiamGiaUpdateRequest dotGiamGiaUpdateRequest) {
        if (dotGiamGiaRepository.existsByDotGiamGiaDangHoatDong(DotGiamGiaStatus.HOAT_DONG, dotGiamGiaUpdateRequest.getNgayKetThuc(), dotGiamGiaUpdateRequest.getId()) ||
                dotGiamGiaRepository.existsByDotGiamGiaDangHoatDong(DotGiamGiaStatus.HOAT_DONG, dotGiamGiaUpdateRequest.getNgayKetThuc(), dotGiamGiaUpdateRequest.getId())
        ) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST, "Ngày bắt đầu hoặc ngày kết thúc đang nằm trong chuỗi ngày hoạt động của 1 sự kiện khác"
                    )
            );
        }
        DotGiamGiaEntity dotGiamGiaEntity = dotGiamGiaRepository.findById(dotGiamGiaUpdateRequest.getId())
                .orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND, "Không tìm thấy đợt giảm giá có id = " + dotGiamGiaUpdateRequest.getId()
                        )
                ));
        if (dotGiamGiaEntity.getNgayBatDau().isBefore(LocalDate.now())) {
            if (dotGiamGiaUpdateRequest.getNgayKetThuc().isBefore(LocalDate.now())) {
                throw new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR, "Không được sửa ngày kết thúc trước ngày hôm nay"
                        )
                );
            }
            dotGiamGiaEntity.setNgayKetThuc(dotGiamGiaUpdateRequest.getNgayKetThuc());
            dotGiamGiaRepository.save(dotGiamGiaEntity);
            return ResponseEntity.ok("UPDATED");
        } else {
            dotGiamGiaEntity.setTenDotGiamGia(dotGiamGiaUpdateRequest.getTenDotGiamGia());
            if (dotGiamGiaUpdateRequest.getNgayBatDau().isBefore(LocalDate.now())) {
                throw new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.BAD_REQUEST, "Ngày bắt đầu không được trước ngày hôm nay"
                        )
                );
            }
            if (dotGiamGiaUpdateRequest.getNgayBatDau().isAfter(dotGiamGiaUpdateRequest.getNgayKetThuc())) {
                throw new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.BAD_REQUEST, "Ngày bắt đầu không được sau ngày kết thúc"
                        )
                );
            }
            dotGiamGiaEntity.setNgayBatDau(dotGiamGiaUpdateRequest.getNgayBatDau());
            dotGiamGiaEntity.setNgayKetThuc(dotGiamGiaUpdateRequest.getNgayKetThuc());
            dotGiamGiaEntity.setLoaiUuDaiDDG(dotGiamGiaUpdateRequest.getLoaiUuDaiDDG());
            switch (dotGiamGiaEntity.getLoaiUuDaiDDG()) {
                case SAN_PHAM -> {
                    sanPhamApDungDDGRepository.deleteAllByDotGiamGiaEntity(dotGiamGiaEntity);
                    dotGiamGiaEntity = loaiUuDaiSanPham(dotGiamGiaEntity, dotGiamGiaUpdateRequest);
                    break;
                }
                case HOA_DON -> {
                    dotGiamGiaEntity = loaiUuDaiHoaDon(dotGiamGiaEntity, dotGiamGiaUpdateRequest);
                    break;
                }
            }
            dotGiamGiaRepository.save(dotGiamGiaEntity);
            return ResponseEntity.ok("UPDATED");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        DotGiamGiaEntity dotGiamGiaEntity = dotGiamGiaRepository.findById(deleteRequest.getId())
                .orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND, "Không tìm thấy đợt giảm giá có id = " + deleteRequest.getId()
                        )
                ));
        dotGiamGiaEntity.setDeleted(true);
        dotGiamGiaRepository.save(dotGiamGiaEntity);
        return ResponseEntity.ok("DELETED");
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }




    @Override
    public DotGiamGiaEntity mappingByCreateRequest(DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        return null;
    }

    @Override
    public DotGiamGiaEntity mappingByUpdateRequest(DotGiamGiaUpdateRequest dotGiamGiaUpdateRequest) {
        return null;
    }

    @Override
    public DotGiamGiaReponse mappingByResponse(DotGiamGiaEntity dotGiamGiaEntity) {
        DotGiamGiaReponse dotGiamGiaReponse = DotGiamGiaReponse.builder()
                .id(dotGiamGiaEntity.getId())
                .tenDotgiamGia(dotGiamGiaEntity.getTenDotGiamGia())
                .ngayBatDau(dotGiamGiaEntity.getNgayBatDau())
                .ngayKetThuc(dotGiamGiaEntity.getNgayKetThuc())
                .loaiUuDaiDDG(dotGiamGiaEntity.getLoaiUuDaiDDG())
                .dotGiamGiaStatus(dotGiamGiaEntity.getDotGiamGiaStatus())
                .build();
        return dotGiamGiaReponse;
    }

    @Override
    public DotGiamGiaResponseDetail mappingResponseDetail(DotGiamGiaEntity dotGiamGiaEntity) {
        DotGiamGiaResponseDetail dotGiamGiaResponseDetail = DotGiamGiaResponseDetail.builder()
                .id(dotGiamGiaEntity.getId())
                .dotGiamGiaStatus(dotGiamGiaEntity.getDotGiamGiaStatus())
                .tenDotGiamGia(dotGiamGiaEntity.getTenDotGiamGia())
                .soTienHoaDonYeuCau(dotGiamGiaEntity.getSoTienHoaDonYeuCau())
                .ngayBatDau(dotGiamGiaEntity.getNgayBatDau())
                .ngayKetThuc(dotGiamGiaEntity.getNgayKetThuc())
                .loaiUuDaiDDG(dotGiamGiaEntity.getLoaiUuDaiDDG())
                .build();
        switch (dotGiamGiaEntity.getLoaiUuDaiDDG()) {
            case SAN_PHAM -> {
                List<SanPhamApDungResponse> sanPhamApDungResponses = dotGiamGiaEntity.getSanPhamApDungDGGEntities()
                        .stream()
                        .map(sanPhamApDungDGGEntity -> {
                            SanPhamApDungResponse sanPhamApDungResponse = new SanPhamApDungResponse();
                            sanPhamApDungResponse.setSanPhamResponse(SanPhamResponse.builder()
                                    .id(sanPhamApDungDGGEntity.getSanPhamEntity().getId())
                                    .tenSanPham(sanPhamApDungDGGEntity.getSanPhamEntity().getTenSanPham())
                                    .build());
                            sanPhamApDungResponse.setGiaTriDuocGiam(sanPhamApDungDGGEntity.getGiaTriDuocGiam());
                            sanPhamApDungResponse.setLoaiUuDai(sanPhamApDungDGGEntity.getLoaiUuDai());
                            return sanPhamApDungResponse;
                        }).toList();
                dotGiamGiaResponseDetail.setSanPhamApDungResponses(sanPhamApDungResponses);
                break;
            }
            case HOA_DON -> {
                dotGiamGiaResponseDetail.setLoaiGiamGiaHD(dotGiamGiaEntity.getLoaiGiamGiaHD());
                dotGiamGiaResponseDetail.setGiaTriGiamHD(dotGiamGiaEntity.getGiaTriGiamHD());
                break;
            }
        }
        return dotGiamGiaResponseDetail;
    }
}
