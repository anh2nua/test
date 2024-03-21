package com.example.fashionmanager.service.impl.module_san_pham.songan;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.request.SoNganUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlysongan.response.SoNganResponse;
import com.example.fashionmanager.entity.SoNganEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.SoNganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SoNganServiceImpl implements SoNganService {
    @Autowired
    private SoNganRepository soNganRepository;

    @Override
    public ResponseEntity<?> getList(SoNganListRequest soNganListRequest) {
        Page<SoNganEntity> soNganEntities = soNganRepository.findAllSoNgan(soNganListRequest.getTenSoNgan() == null ?
                null : "%" + soNganListRequest.getTenSoNgan() + "%", PageRequest.of(soNganListRequest.getPage(), soNganListRequest.getSize()));
        ListReponseDto<SoNganResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(soNganEntities.stream().map(this::mappingByResponse).collect(Collectors.toList()));
        listReponseDto.setPageSize(soNganEntities.getSize());
        listReponseDto.setPageCount(soNganEntities.getTotalPages());
        listReponseDto.setPageIndex(soNganListRequest.getPage());
        listReponseDto.setHasNextPage(soNganEntities.hasNext());
        listReponseDto.setHasPreviousPage(soNganEntities.hasPrevious());
        listReponseDto.setTotalItemCount(soNganEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        SoNganEntity soNganEntity = soNganRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy số ngăn có id = " + id)));
        return ResponseEntity.ok(mappingResponseDetail(soNganEntity));
    }

    @Override
    public ResponseEntity<?> create(SoNganCreateRequest soNganCreateRequest) {
        SoNganEntity soNganEntity = mappingByCreateRequest(soNganCreateRequest);
        soNganRepository.save(soNganEntity);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(SoNganUpdateRequest soNganUpdateRequest) {
        if (!soNganRepository.existsById(soNganUpdateRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy số ngăn có id = " + soNganUpdateRequest.getId()));
        }
        SoNganEntity soNganEntity = mappingByUpdateRequest(soNganUpdateRequest);
        soNganRepository.save(soNganEntity);
        return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<SoNganEntity> soNganEntities = soNganRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(soNganEntity -> soNganEntity.setDeleted(deleteRequest.getDeleted())).toList();
            soNganRepository.saveAll(soNganEntities);
        } else {
            SoNganEntity soNganEntity = soNganRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            soNganEntity.setDeleted(deleteRequest.getDeleted());
            soNganRepository.save(soNganEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }

    @Override
    public SoNganEntity mappingByCreateRequest(SoNganCreateRequest soNganCreateRequest) {
        return SoNganEntity.builder().tenSoNgan(soNganCreateRequest.getTenSoNgan()).build();
    }

    @Override
    public SoNganEntity mappingByUpdateRequest(SoNganUpdateRequest soNganUpdateRequest) {
        return SoNganEntity.builder().tenSoNgan(soNganUpdateRequest.getTenSoNgan()).id(soNganUpdateRequest.getId()).build();
    }

    @Override
    public SoNganResponse mappingByResponse(SoNganEntity soNganEntity) {
        return SoNganResponse.builder().tenSoNgan(soNganEntity.getTenSoNgan()).id(soNganEntity.getId()).build();
    }

    @Override
    public SoNganResponse mappingResponseDetail(SoNganEntity soNganEntity) {
        return null;
    }
}
