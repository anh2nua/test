package com.example.fashionmanager.service.impl.module_san_pham.thuonghieu;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.CreateThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.ListThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.request.UpdateThuongHieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.response.ThuongHieuDetailReponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlythuonghieu.response.ThuongHieuReponse;
import com.example.fashionmanager.entity.ThuongHieuEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.ThuongHieuRepository;
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

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService{
    @Autowired
    ThuongHieuRepository thuongHieuRepository;
    @Override
    public ResponseEntity<ListReponseDto<ThuongHieuReponse>> getList(ListThuongHieuRequest listThuongHieuRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listThuongHieuRequest.getPage(), listThuongHieuRequest.getSize(), sort);

        Specification<ThuongHieuEntity> thuongHieuEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listThuongHieuRequest.getTenThuongHieu())) {
                predicates.add(criteriaBuilder.like(root.get("tenThuongHieu"), "%" + listThuongHieuRequest.getTenThuongHieu() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<ThuongHieuEntity> thuongHieuEntities = thuongHieuRepository.findAll(thuongHieuEntitySpecification, pageable);
        List<ThuongHieuReponse> thuongHieuReponses = thuongHieuEntities.stream().map(thuongHieuEntity -> mappingByResponse(thuongHieuEntity)).toList();
        ListReponseDto<ThuongHieuReponse> listReponseDto = new ListReponseDto<ThuongHieuReponse>();
        listReponseDto.setItems(thuongHieuReponses);
        listReponseDto.setHasNextPage(thuongHieuEntities.hasNext());
        listReponseDto.setHasPreviousPage(thuongHieuEntities.hasPrevious());
        listReponseDto.setPageCount(thuongHieuEntities.getTotalPages());
        listReponseDto.setPageSize(thuongHieuEntities.getSize());
        listReponseDto.setTotalItemCount(thuongHieuEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<ThuongHieuDetailReponse> getById(Long id) {
        ThuongHieuEntity thuongHieuEntity = thuongHieuRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(thuongHieuEntity));

    }

    @Override
    public ResponseEntity<ThuongHieuReponse> create(CreateThuongHieuRequest createThuongHieuRequest) {
        ThuongHieuEntity thuongHieuEntity = mappingByCreateRequest(createThuongHieuRequest);
        thuongHieuRepository.save(thuongHieuEntity);
        return ResponseEntity.ok(mappingByResponse(thuongHieuEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateThuongHieuRequest updateThuongHieuRequest) {
        if (!thuongHieuRepository.existsById(updateThuongHieuRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        ThuongHieuEntity entity = mappingByUpdateRequest(updateThuongHieuRequest);
        thuongHieuRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<ThuongHieuEntity> hoaTietEntities = thuongHieuRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(thuongHieuEntity -> thuongHieuEntity.setDeleted(deleteRequest.getDeleted())).toList();
            thuongHieuRepository.saveAll(hoaTietEntities);
        } else {
            ThuongHieuEntity thuongHieuEntity = thuongHieuRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            thuongHieuEntity.setDeleted(deleteRequest.getDeleted());
            thuongHieuRepository.save(thuongHieuEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }




    @Override
    public ThuongHieuEntity mappingByCreateRequest(CreateThuongHieuRequest createThuongHieuRequest) {
        return ThuongHieuEntity.builder()
                .tenThuongHieu(createThuongHieuRequest.getTenThuongHieu())
                .build();
    }

    @Override
    public ThuongHieuEntity mappingByUpdateRequest(UpdateThuongHieuRequest updateThuongHieuRequest) {
        return ThuongHieuEntity.builder()
                .id(updateThuongHieuRequest.getId())
                .tenThuongHieu(updateThuongHieuRequest.getTenThuongHieu())
                .build();
    }

    @Override
    public ThuongHieuReponse mappingByResponse(ThuongHieuEntity thuongHieuEntity) {
        return ThuongHieuReponse.builder()
                .tenThuongHieu(thuongHieuEntity.getTenThuongHieu())
                .id(thuongHieuEntity.getId())
                .build();
    }

    @Override
    public ThuongHieuDetailReponse mappingResponseDetail(ThuongHieuEntity thuongHieuEntity) {
        return ThuongHieuDetailReponse.builder()
                .id(thuongHieuEntity.getId())
                .tenThuongHieu(thuongHieuEntity.getTenThuongHieu())
                .build();
    }
}
