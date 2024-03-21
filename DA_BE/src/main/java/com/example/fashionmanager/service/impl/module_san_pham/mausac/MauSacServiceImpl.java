package com.example.fashionmanager.service.impl.module_san_pham.mausac;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.CreateMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.ListMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.request.UpdateMauSacRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response.MauSacDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response.MauSacResponse;
import com.example.fashionmanager.entity.MauSacEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.MauSacRepository;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MauSacServiceImpl implements MauSacService{

    @Autowired
    private MauSacRepository mauSacRepository;
    @Override
    public ResponseEntity<ListReponseDto<MauSacResponse>> getList(ListMauSacRequest listMauSacRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listMauSacRequest.getPage(), listMauSacRequest.getSize(), sort);

        Specification<MauSacEntity> mauSacEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listMauSacRequest.getTenMau())) {
                predicates.add(criteriaBuilder.like(root.get("tenMau"), "%" + listMauSacRequest.getTenMau() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<MauSacEntity> mauSacEntities = mauSacRepository.findAll(mauSacEntitySpecification, pageable);
        List<MauSacResponse> mauSacResponses = mauSacEntities.stream().map(mauSacEntity -> mappingByResponse(mauSacEntity)).toList();
        ListReponseDto<MauSacResponse> listReponseDto = new ListReponseDto<MauSacResponse>();
        listReponseDto.setItems(mauSacResponses);
        listReponseDto.setHasNextPage(mauSacEntities.hasNext());
        listReponseDto.setHasPreviousPage(mauSacEntities.hasPrevious());
        listReponseDto.setPageCount(mauSacEntities.getTotalPages());
        listReponseDto.setPageSize(mauSacEntities.getSize());
        listReponseDto.setTotalItemCount(mauSacEntities.getTotalElements());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<MauSacDetailResponse> getById(Long id) {
        MauSacEntity mauSacEntity = mauSacRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(mauSacEntity));    }

    @Override
    public ResponseEntity<MauSacResponse> create(CreateMauSacRequest createMauSacRequest) {
        MauSacEntity mauSacEntity = mappingByCreateRequest(createMauSacRequest);
        mauSacRepository.save(mauSacEntity);
        return ResponseEntity.ok(mappingByResponse(mauSacEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateMauSacRequest updateMauSacRequest) {
        if (!mauSacRepository.existsById(updateMauSacRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        MauSacEntity entity = mappingByUpdateRequest(updateMauSacRequest);
        mauSacRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<MauSacEntity> mauSacEntities = mauSacRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(mauSacEntity -> mauSacEntity.setDeleted(deleteRequest.getDeleted())).toList();
            mauSacRepository.saveAll(mauSacEntities);
        } else {
            MauSacEntity mauSacEntity = mauSacRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            mauSacEntity.setDeleted(deleteRequest.getDeleted());
            mauSacRepository.save(mauSacEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }



    @Override
    public MauSacEntity mappingByCreateRequest(CreateMauSacRequest createMauSacRequest) {
        return MauSacEntity.builder().maMau(createMauSacRequest.getMaMau()).tenMau(createMauSacRequest.getTenMau()).build();
    }

    @Override
    public MauSacEntity mappingByUpdateRequest(UpdateMauSacRequest updateMauSacRequest) {
        return MauSacEntity.builder().maMau(updateMauSacRequest.getMaMau())
                .tenMau(updateMauSacRequest.getTenMau()).id(updateMauSacRequest.getId()).build();
    }

    @Override
    public MauSacResponse mappingByResponse(MauSacEntity mauSacEntity) {
        return MauSacResponse.builder().tenMau(mauSacEntity.getTenMau()).maMau(mauSacEntity.getMaMau()).id(mauSacEntity.getId()).build();
    }

    @Override
    public MauSacDetailResponse mappingResponseDetail(MauSacEntity mauSacEntity) {
        return MauSacDetailResponse.builder()
                .maMau(mauSacEntity.getMaMau())
                .tenMau(mauSacEntity.getTenMau())
                .id(mauSacEntity.getId()).build();
    }
}
