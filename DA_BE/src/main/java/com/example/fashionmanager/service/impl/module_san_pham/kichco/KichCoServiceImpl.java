package com.example.fashionmanager.service.impl.module_san_pham.kichco;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KichCoCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KichCoListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.request.KickCoUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykichco.response.KichCoResponse;
import com.example.fashionmanager.entity.KichCoEntity;
import com.example.fashionmanager.repository.KichCoRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class KichCoServiceImpl implements KichCoService{
    @Autowired
    KichCoRepository kichCoRepository;
    @Override
    public ResponseEntity<?> getList(KichCoListRequest kichCoListRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(kichCoListRequest.getPage(), kichCoListRequest.getSize(), sort);

        Specification<KichCoEntity> kichCoEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(kichCoListRequest.getTenKichCo())) {
                predicates.add(criteriaBuilder.like(root.get("tenKichCo"), "%" + kichCoListRequest.getTenKichCo() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<KichCoEntity> kichCoEntities = kichCoRepository.findAll(kichCoEntitySpecification, pageable);
        List<KichCoResponse> kichCoResponses = kichCoEntities.stream().map(kichCoEntity  -> mappingByResponse(kichCoEntity)).toList();
        ListReponseDto<KichCoResponse> listReponseDto = new ListReponseDto<KichCoResponse>();
        listReponseDto.setItems(kichCoResponses);
        listReponseDto.setHasNextPage(kichCoEntities.hasNext());
        listReponseDto.setHasPreviousPage(kichCoEntities.hasPrevious());
        listReponseDto.setPageCount(kichCoEntities.getTotalPages());
        listReponseDto.setPageSize(kichCoEntities.getSize());
        listReponseDto.setTotalItemCount(kichCoEntities.getTotalElements());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return ResponseEntity.ok(mappingResponseDetail(kichCoRepository.findById(id).get()));
    }

    @Override
    public ResponseEntity<?> create(KichCoCreateRequest kichCoCreateRequest) {
        kichCoRepository.save(mappingByCreateRequest(kichCoCreateRequest));
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> update(KickCoUpdateRequest kickCoUpdateRequest) {
        kichCoRepository.save(mappingByUpdateRequest(kickCoUpdateRequest));
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        kichCoRepository.deleteById(deleteRequest.getId());
        return ResponseEntity.ok("DELETED");
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }


    @Override
    public KichCoEntity mappingByCreateRequest(KichCoCreateRequest kichCoCreateRequest) {
        return KichCoEntity.builder().tenKichCo(kichCoCreateRequest.getTenKichCo()).build();
    }

    @Override
    public KichCoEntity mappingByUpdateRequest(KickCoUpdateRequest kickCoUpdateRequest) {
        return KichCoEntity.builder().tenKichCo(kickCoUpdateRequest.getTenKichCo()).id(kickCoUpdateRequest.getId()).build();
    }

    @Override
    public KichCoResponse mappingByResponse(KichCoEntity kichCoEntity) {
        return KichCoResponse.builder().id(kichCoEntity.getId()).tenKichCo(kichCoEntity.getTenKichCo()).build();
    }

    @Override
    public KichCoResponse mappingResponseDetail(KichCoEntity kichCoEntity) {
        return KichCoResponse.builder().id(kichCoEntity.getId()).tenKichCo(kichCoEntity.getTenKichCo()).build();
    }
}
