package com.example.fashionmanager.service.impl.module_san_pham.kichthuoc;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response.KichCoDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response.KichThuocDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.response.KichThuocResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.CreateKichThuocRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.ListKichThuocRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichthuoc.request.UpdateKichThuocRequest;
import com.example.fashionmanager.entity.KichCoEntity;
import com.example.fashionmanager.entity.KichThuocEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.KichCoRepository;
import com.example.fashionmanager.repository.KichThuocRepository;
import jakarta.persistence.criteria.Predicate;
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
public class KichThuocServiceImpl implements KichThuocService {
    @Autowired
    KichThuocRepository kichThuocRepository;
    @Autowired
    KichCoRepository kichCoRepository;

    @Override
    public ResponseEntity<ListReponseDto<KichThuocResponse>> getList(ListKichThuocRequest listKichThuocRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listKichThuocRequest.getPage(), listKichThuocRequest.getSize(), sort);

        Specification<KichThuocEntity> kichThuocEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(listKichThuocRequest.getKichCoId())) {
                predicates.add(criteriaBuilder.equal(root.get("kichCoEntity.id"), listKichThuocRequest.getKichCoId()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<KichThuocEntity> kichThuocEntities = kichThuocRepository.findAll(kichThuocEntitySpecification, pageable);
        List<KichThuocResponse> kichThuocReponses = kichThuocEntities.stream().map(kichThuocEntity -> mappingByResponse(kichThuocEntity)).toList();
        ListReponseDto<KichThuocResponse> listReponseDto = new ListReponseDto<KichThuocResponse>();
        listReponseDto.setItems(kichThuocReponses);
        listReponseDto.setHasNextPage(kichThuocEntities.hasNext());
        listReponseDto.setHasPreviousPage(kichThuocEntities.hasPrevious());
        listReponseDto.setPageCount(kichThuocEntities.getTotalPages());
        listReponseDto.setPageSize(kichThuocEntities.getSize());
        listReponseDto.setTotalItemCount(kichThuocEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<KichThuocDetailResponse> getById(Long id) {
        KichThuocEntity kichThuocEntity = kichThuocRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy kích thước có id =" + id)));
        return ResponseEntity.ok(mappingResponseDetail(kichThuocEntity));
    }

    @Override
    public ResponseEntity<KichThuocResponse> create(CreateKichThuocRequest createKichThuocRequest) {
        KichThuocEntity kichThuocEntity = mappingByCreateRequest(createKichThuocRequest);
        kichThuocRepository.save(kichThuocEntity);
        return ResponseEntity.ok(mappingByResponse(kichThuocEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateKichThuocRequest updateKichThuocRequest) {
        if (!kichThuocRepository.existsById(updateKichThuocRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy kích thước có id =" + updateKichThuocRequest.getId()));
        }
        KichThuocEntity entity = mappingByUpdateRequest(updateKichThuocRequest);
        kichThuocRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<KichThuocEntity> chatLieuEntities = kichThuocRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(kichThuocEntity -> kichThuocEntity.setDeleted(deleteRequest.getDeleted())).toList();
            kichThuocRepository.saveAll(chatLieuEntities);
        } else {
            KichThuocEntity kichThuocEntity = kichThuocRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            kichThuocEntity.setDeleted(deleteRequest.getDeleted());
            kichThuocRepository.save(kichThuocEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }





    @Override
    public KichThuocEntity mappingByCreateRequest(CreateKichThuocRequest createKichThuocRequest) {
        KichCoEntity kichCoEntity = kichCoRepository.findById(createKichThuocRequest.getKichCoId())
                .orElseThrow(() -> new FashionManagerException(
                                new ErrorResponse(
                                        HttpStatus.NOT_FOUND,
                                        "Không tìm thấy kích cỡ có id = " + createKichThuocRequest.getKichCoId()
                                )
                        )
                );
        return KichThuocEntity.builder()
                .kichCoEntity(kichCoEntity)
                .chieuCao(createKichThuocRequest.getChieuCao())
                .chieuDai(createKichThuocRequest.getChieuDai())
                .chieuRong(createKichThuocRequest.getChieuRong())
                .build();
    }

    @Override
    public KichThuocEntity mappingByUpdateRequest(UpdateKichThuocRequest updateKichThuocRequest) {
        KichCoEntity kichCoEntity = kichCoRepository.findById(updateKichThuocRequest.getKichCoId())
                .orElseThrow(() -> new FashionManagerException(
                                new ErrorResponse(
                                        HttpStatus.NOT_FOUND,
                                        "Không tìm thấy kích cỡ có id = " + updateKichThuocRequest.getKichCoId()
                                )
                        )
                );
        return KichThuocEntity.builder()
                .id(updateKichThuocRequest.getId())
                .kichCoEntity(kichCoEntity)
                .chieuCao(updateKichThuocRequest.getChieuCao())
                .chieuDai(updateKichThuocRequest.getChieuDai())
                .chieuRong(updateKichThuocRequest.getChieuRong())
                .build();
    }

    @Override
    public KichThuocResponse mappingByResponse(KichThuocEntity kichThuocEntity) {
        return KichThuocResponse.builder()
                .id(kichThuocEntity.getId())
                .kichCoDetailResponse(KichCoDetailResponse.builder().tenKichCo(kichThuocEntity.getKichCoEntity().getTenKichCo()).id(kichThuocEntity.getKichCoEntity().getId()).build())
                .chieuCao(kichThuocEntity.getChieuCao())
                .chieuDai(kichThuocEntity.getChieuDai())
                .chieuRong(kichThuocEntity.getChieuRong())
                .build();
    }

    @Override
    public KichThuocDetailResponse mappingResponseDetail(KichThuocEntity kichThuocEntity) {
        return KichThuocDetailResponse.builder()
                .id(kichThuocEntity.getId())
                .kichCoDetailResponse(KichCoDetailResponse.builder().tenKichCo(kichThuocEntity.getKichCoEntity().getTenKichCo()).id(kichThuocEntity.getKichCoEntity().getId()).build())
                .chieuCao(kichThuocEntity.getChieuCao())
                .chieuDai(kichThuocEntity.getChieuDai())
                .chieuRong(kichThuocEntity.getChieuRong())
                .build();
    }
}
