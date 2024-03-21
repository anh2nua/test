package com.example.fashionmanager.service.impl.module_san_pham.chitietsanpham;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlyctsp.response.ChiTietSanPhamDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlyctsp.response.ChiTietSanPhamResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlysanpham.request.SanPhamListRequest;
import com.example.fashionmanager.entity.ChiTietSanPhamEntity;
import com.example.fashionmanager.entity.HinhAnhEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.ChiTietSanPhamRepository;
import com.example.fashionmanager.service.impl.module_san_pham.mausac.MauSacService;
import com.example.fashionmanager.service.impl.module_san_pham.quanlysanpham.SanPhamService;
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
import java.util.stream.Collectors;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    MauSacService mauSacService;

    @Override

    public ResponseEntity<?> getListSanPham(SanPhamListRequest sanPhamListRequest) {

        Specification<ChiTietSanPhamEntity> chiTietSanPhamEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(sanPhamListRequest.getMaSanPham())) {
                predicates.add(criteriaBuilder.like(root.join("sanPhamEntity").get("maSanPham"), "%" + sanPhamListRequest.getMaSanPham() + "%"));
            }
            if (StringUtils.isNotBlank(sanPhamListRequest.getTenSanPham())) {
                predicates.add(criteriaBuilder.like(root.join("sanPhamEntity").get("tenSanPham"), "%" + sanPhamListRequest.getTenSanPham() + "%"));
            }
            if (sanPhamListRequest.getLoaiSanPhamId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("sanPhamEntity").join("loaiSanPhamEntity").get("id"), sanPhamListRequest.getLoaiSanPhamId()));
            }
            if (sanPhamListRequest.getThuongHieuId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("sanPhamEntity").join("thuongHieuEntity").get("id"), sanPhamListRequest.getThuongHieuId()));
            }
            if (sanPhamListRequest.getDanhMucIds() != null && !sanPhamListRequest.getDanhMucIds().isEmpty()) {
                root.join("sanPhamEntity").join("sanPhamDanhMucEntities", JoinType.INNER);
                root.join("sanPhamEntity").join("sanPhamDanhMucEntities").join("danhMucEntity", JoinType.INNER);
                predicates.add((root.join("sanPhamDanhMucEntities").join("danhMucEntity").get("id").in(sanPhamListRequest.getDanhMucIds())));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<ChiTietSanPhamEntity> chiTietSanPhamEntities = chiTietSanPhamRepository.findAll(chiTietSanPhamEntitySpecification, getPageable(sanPhamListRequest));
        List<ChiTietSanPhamResponse> chiTietSanPhamResponses = chiTietSanPhamEntities.stream().map(this::mappingByResponse).toList();
        ListReponseDto<ChiTietSanPhamResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(chiTietSanPhamResponses);
        listReponseDto.setHasNextPage(chiTietSanPhamEntities.hasNext());
        listReponseDto.setHasPreviousPage(chiTietSanPhamEntities.hasPrevious());
        listReponseDto.setPageCount(chiTietSanPhamEntities.getTotalPages());
        listReponseDto.setPageSize(chiTietSanPhamEntities.getSize());
        listReponseDto.setTotalItemCount(chiTietSanPhamEntities.getTotalElements());
        return new ResponseEntity<>(listReponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getListSanPhamDetail(Long id) {
        ChiTietSanPhamEntity chiTietSanPham = chiTietSanPhamRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND,"Không tìm thấy sản phẩm có id = "+ id)));
        return ResponseEntity.ok(mappingByReponseDetail(chiTietSanPham));
    }

    private ChiTietSanPhamResponse mappingByResponse(ChiTietSanPhamEntity chiTietSanPhamEntity) {
        HinhAnhEntity hinhAnhEntity = chiTietSanPhamEntity.getHinhAnhEntities().stream().findFirst().orElse(null);
        return ChiTietSanPhamResponse.builder().id(chiTietSanPhamEntity.getId()).idSanPham(chiTietSanPhamEntity.getSanPhamEntity().getId()).tenSanPham(chiTietSanPhamEntity.getSanPhamEntity().getTenSanPham()).giaNiemYet(chiTietSanPhamEntity.getGiaBanNiemYet()).kieuGiamGia(chiTietSanPhamEntity.getHinhThucGiamGia()).giaTriGiam(chiTietSanPhamEntity.getGiaTriDuocGiam()).giaCuoiCung(chiTietSanPhamEntity.getGiaBanCuoiCung()).anhId(hinhAnhEntity != null ? hinhAnhEntity.getId() : null).build();
    }
    private ChiTietSanPhamDetailResponse mappingByReponseDetail(ChiTietSanPhamEntity chiTietSanPhamEntity){
        return ChiTietSanPhamDetailResponse.builder()
                .id(chiTietSanPhamEntity.getId())
                .sanPhamDetailResponse(sanPhamService.mappingResponseDetail(chiTietSanPhamEntity.getSanPhamEntity()))
                .mauSacResponse(mauSacService.mappingResponseDetail(chiTietSanPhamEntity.getMauSacEntity()))
                .giaBanNiemYet(chiTietSanPhamEntity.getGiaBanNiemYet())
                .giaBanNiemYet(chiTietSanPhamEntity.getGiaBanNiemYet())
                .hinhThucGiamGia(chiTietSanPhamEntity.getHinhThucGiamGia())
                .giaTriDuocGiam(chiTietSanPhamEntity.getGiaTriDuocGiam())
                .giaBanCuoiCung(chiTietSanPhamEntity.getGiaBanCuoiCung())
                .hinhAnhIds(chiTietSanPhamEntity.getHinhAnhEntities().stream().map(HinhAnhEntity :: getId).collect(Collectors.toList()))
                .build();
    }

    private Pageable getPageable(SanPhamListRequest sanPhamListRequest) {
        Sort sort = null;
        if (sanPhamListRequest.getSanPhamOrderBy() != null) {
            switch (sanPhamListRequest.getSanPhamOrderBy()) {
                case NGAYTAO -> {
                    sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate")

                    );
                    break;
                }
                case GIA_CAODENTHAP -> {
//                sort = Sort.by(
//                        new Sort.Order(Sort.Direction.DESC, "dateCreate")
//
//                );
                    break;
                }
                case GIA_THAPDENCAO -> {
//                sort = Sort.by(
//                        new Sort.Order(Sort.Direction.DESC, "dateCreate")
//
//                );
                    break;
                }
                case TEN_A_Z -> {
                    sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "sanPhamEntity.tenSanPhamn")

                    );
                    break;
                }
                case TEN_Z_A -> {
                    sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "sanPhamEntity.tenSanPhamn")

                    );
                    break;
                }
                default -> {
                    sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate")

                    );
                    break;
                }
            }
        }
        return PageRequest.of(sanPhamListRequest.getPage(), sanPhamListRequest.getSize(), sort);
    }
}
