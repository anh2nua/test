package com.example.fashionmanager.service.impl.module_san_pham.danhmuc;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.request.DanhMucUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response.DanhMucDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlydanhmuc.response.DanhMucResponse;
import com.example.fashionmanager.entity.DanhMucEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.DanhMucRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DanhMucServiceImpl implements DanhMucService {
    @Autowired
    DanhMucRepository danhMucRepository;

    @Override
    public ResponseEntity<?> getList(DanhMucListRequest danhMucListRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(danhMucListRequest.getPage(), danhMucListRequest.getSize(), sort);
        Specification<DanhMucEntity> danhMucEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(danhMucListRequest.getMaDanhMuc())) {
                predicates.add(criteriaBuilder.like(root.get("maDanhMuc"), "%" + danhMucListRequest.getMaDanhMuc() + "%"));
            }
            if (StringUtils.isNotBlank(danhMucListRequest.getTenDanhMuc())) {
                predicates.add(criteriaBuilder.like(root.get("tenDanhMuc"), "%" + danhMucListRequest.getMaDanhMuc() + "%"));
            }
            if (Objects.nonNull(danhMucListRequest.getIdDanhMucParent())) {
                predicates.add(criteriaBuilder.equal(root.get("danhMucEntity.id"), danhMucListRequest.getIdDanhMucParent()));
            }
            if (Objects.nonNull(danhMucListRequest.getIsParent())) {
                predicates.add(criteriaBuilder.equal(root.get("isParent"), danhMucListRequest.getIsParent()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<DanhMucEntity> danhMucEntities = danhMucRepository.findAll(danhMucEntitySpecification, pageable);
        List<DanhMucResponse> danhMucResponses = danhMucEntities.map(this::mappingByResponse).stream().toList();
        ListReponseDto<DanhMucResponse> listReponseDto = new ListReponseDto<DanhMucResponse>();
        listReponseDto.setItems(danhMucResponses);
        listReponseDto.setHasNextPage(danhMucEntities.hasNext());
        listReponseDto.setHasPreviousPage(danhMucEntities.hasPrevious());
        listReponseDto.setPageCount(danhMucEntities.getTotalPages());
        listReponseDto.setPageSize(danhMucEntities.getSize());
        listReponseDto.setTotalItemCount(danhMucEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return ResponseEntity.ok(mappingResponseDetail(danhMucRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục có id = " + id + " này")))));
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(DanhMucCreateRequest danhMucCreateRequest) {
        danhMucRepository.save(mappingByCreateRequest(danhMucCreateRequest));
        return ResponseEntity.ok("CREATED");
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(DanhMucUpdateRequest danhMucUpdateRequest) {
        if (!danhMucRepository.existsById(danhMucUpdateRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục có id = " + danhMucUpdateRequest.getId() + " này"));
        }
        danhMucRepository.save(mappingByUpdateRequest(danhMucUpdateRequest));
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        DanhMucEntity danhMucEntity = danhMucRepository.findById(deleteRequest.getId()).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục có id = " + deleteRequest.getId() + " này")));
        if (danhMucEntity.getIsParent()) {
            Set<DanhMucEntity> danhMucEntities = danhMucEntity.getDanhMucEntities();
            danhMucEntities.stream().peek(danhMucEntity1 -> danhMucEntity1.setDanhMucEntity(null)).collect(Collectors.toSet());
            danhMucEntity.setDanhMucEntities(null);
            danhMucRepository.saveAll(danhMucEntities);
        } else {
            danhMucEntity.setDanhMucEntity(null);
        }
        danhMucEntity.setDeleted(true);
        danhMucRepository.save(danhMucEntity);
        return ResponseEntity.ok("DELETED");
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }



    @Override
    public DanhMucEntity mappingByCreateRequest(DanhMucCreateRequest danhMucCreateRequest) {
        DanhMucEntity danhMucEntity = DanhMucEntity.builder().maDanhMuc(danhMucCreateRequest.getMaDanhMuc()).tenDanhMuc(danhMucCreateRequest.getTenDanhMuc()).isParent(danhMucCreateRequest.getIsParent()).build();
        if (danhMucCreateRequest.getIsParent()) {
            if (danhMucEntity.getId() != null) {
                danhMucEntity.setDanhMucEntity(null);
            }
            if (!danhMucCreateRequest.getDanhMucs().isEmpty()) {
                Set<DanhMucEntity> danhMucEntities = danhMucRepository.findAllById(danhMucCreateRequest.getDanhMucs()).stream().collect(Collectors.toSet());
                danhMucEntities.stream().map(o -> {
                    o.setDanhMucEntity(danhMucEntity);
                    return o;
                }).collect(Collectors.toSet());

                danhMucEntity.setDanhMucEntities(danhMucEntities);
            }
        } else {
            if (danhMucEntity.getId() != null) {
                danhMucEntity.setDanhMucEntities(null);
            }
            DanhMucEntity danhMucEntityParent = danhMucRepository.findById(danhMucCreateRequest.getDanhMucId()).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục cha này")));
            danhMucEntity.setDanhMucEntity(danhMucEntityParent);
        }
        return danhMucEntity;
    }

    @Override
    public DanhMucEntity mappingByUpdateRequest(DanhMucUpdateRequest danhMucUpdateRequest) {
        DanhMucEntity danhMucEntity = DanhMucEntity.builder().id(danhMucUpdateRequest.getId()).maDanhMuc(danhMucUpdateRequest.getMaDanhMuc()).tenDanhMuc(danhMucUpdateRequest.getTenDanhMuc()).isParent(danhMucUpdateRequest.getIsParent()).build();
        if (danhMucUpdateRequest.getIsParent()) {
            danhMucEntity.setDanhMucEntity(null);
            if (!danhMucUpdateRequest.getDanhMucs().isEmpty()) {
                Set<DanhMucEntity> danhMucEntities = danhMucRepository.findAllById(danhMucUpdateRequest.getDanhMucs()).stream().collect(Collectors.toSet());
                danhMucEntities.stream().map(o -> {
                    o.setDanhMucEntity(danhMucEntity);
                    return o;
                }).collect(Collectors.toSet());

                danhMucEntity.setDanhMucEntities(danhMucEntities);
            }
        } else {
            danhMucEntity.setDanhMucEntities(null);
            DanhMucEntity danhMucEntityParent = danhMucRepository.findById(danhMucUpdateRequest.getDanhMucId()).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục cha này")));
            danhMucEntity.setDanhMucEntity(danhMucEntityParent);
        }
        return danhMucEntity;
    }

    @Override
    public DanhMucResponse mappingByResponse(DanhMucEntity danhMucEntity) {
        return DanhMucResponse.builder().id(danhMucEntity.getId()).maDanhMuc(danhMucEntity.getMaDanhMuc()).tenDanhMuc(danhMucEntity.getTenDanhMuc()).isParent(danhMucEntity.getIsParent()).tenDanhMucParent(Objects.isNull(danhMucEntity.getDanhMucEntity()) ? null : danhMucEntity.getDanhMucEntity().getTenDanhMuc()).build();
    }

    @Override
    public DanhMucDetailResponse mappingResponseDetail(DanhMucEntity danhMucEntity) {
        DanhMucDetailResponse danhMucDetailResponse = new DanhMucDetailResponse();
        danhMucDetailResponse.setId(danhMucEntity.getId());
        danhMucDetailResponse.setMaDanhMuc(danhMucEntity.getMaDanhMuc());
        danhMucDetailResponse.setTenDanhMuc(danhMucEntity.getTenDanhMuc());
        danhMucDetailResponse.setDanhMucParent(Objects.isNull(danhMucEntity.getDanhMucEntity()) ? null : mappingResponseDetail(danhMucEntity.getDanhMucEntity()));
        List<DanhMucResponse> danhMucResponses = danhMucEntity.getDanhMucEntities().stream().map(this::mappingByResponse).collect(Collectors.toList());
        danhMucDetailResponse.setDanhMucResponses(danhMucResponses);
        danhMucDetailResponse.setIsParent(danhMucEntity.getIsParent());
        return danhMucDetailResponse;
    }

    @Override
    public List<DanhMucDetailResponse> getDanhMucParent() {
        return danhMucRepository.findAllByIsParentAndDeletedIsFalse(true).stream().map(this::mappingResponseDetail).collect(Collectors.toList());
    }

    @Override
    public List<DanhMucDetailResponse> getDanhMucChild() {
        return danhMucRepository.findAllByIsParentAndDeletedIsFalse(false).stream().map(this::mappingResponseDetail).collect(Collectors.toList());
    }
}
