package com.example.fashionmanager.service.impl.module_san_pham.kieukhoa;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.Response.KieuKhoaResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.CreateKieuKhoaRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.ListKieuKhoaRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlykieukhoa.request.UpdateKieuKhoaRequest;
import com.example.fashionmanager.entity.KieuKhoaEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.KieuKhoaRepository;
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
public class KieuKhoaServiceImpl implements KieuKhoaService {
    @Autowired
    private KieuKhoaRepository kieuKhoaRepository;

    @Override
    public ResponseEntity<?> getList(ListKieuKhoaRequest listKieuKhoaRequest) {
        Page<KieuKhoaEntity> kieuKhoaEntities = kieuKhoaRepository.findAllKieuKhoa(listKieuKhoaRequest.getTenKieuKhoa() == null ?
                null : "%" + listKieuKhoaRequest.getTenKieuKhoa() + "%", PageRequest.of(listKieuKhoaRequest.getPage(), listKieuKhoaRequest.getSize()));
        ListReponseDto<KieuKhoaResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(kieuKhoaEntities.stream().map(this::mappingByResponse).collect(Collectors.toList()));
        listReponseDto.setPageSize(kieuKhoaEntities.getSize());
        listReponseDto.setPageCount(kieuKhoaEntities.getTotalPages());
        listReponseDto.setPageIndex(listKieuKhoaRequest.getPage());
        listReponseDto.setHasNextPage(kieuKhoaEntities.hasNext());
        listReponseDto.setHasPreviousPage(kieuKhoaEntities.hasPrevious());
        listReponseDto.setTotalItemCount(kieuKhoaEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        KieuKhoaEntity kieuKhoaEntity = kieuKhoaRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + id)));
        return ResponseEntity.ok(mappingByResponse(kieuKhoaEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateKieuKhoaRequest createKieuKhoaRequest) {
        KieuKhoaEntity kieuKhoaEntity = mappingByCreateRequest(createKieuKhoaRequest);
        kieuKhoaRepository.save(kieuKhoaEntity);
        return new ResponseEntity<>("CREATED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(UpdateKieuKhoaRequest updateKieuKhoaRequest) {
        if (kieuKhoaRepository.existsById(updateKieuKhoaRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + updateKieuKhoaRequest.getId()));
        }
        KieuKhoaEntity kieuKhoaEntity = mappingByUpdateRequest(updateKieuKhoaRequest);
        kieuKhoaRepository.save(kieuKhoaEntity);
        return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<KieuKhoaEntity> kieuKhoaEntities = kieuKhoaRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(kieuKhoaEntity -> kieuKhoaEntity.setDeleted(deleteRequest.getDeleted())).toList();
            kieuKhoaRepository.saveAll(kieuKhoaEntities);
        } else {
            KieuKhoaEntity kieuKhoaEntity = kieuKhoaRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            kieuKhoaEntity.setDeleted(deleteRequest.getDeleted());
            kieuKhoaRepository.save(kieuKhoaEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }



    @Override
    public KieuKhoaEntity mappingByCreateRequest(CreateKieuKhoaRequest createKieuKhoaRequest) {
        return KieuKhoaEntity.builder().tenKieuKhoa(createKieuKhoaRequest.getTenKieuKhoa()).build();
    }

    @Override
    public KieuKhoaEntity mappingByUpdateRequest(UpdateKieuKhoaRequest updateKieuKhoaRequest) {
        return KieuKhoaEntity.builder().tenKieuKhoa(updateKieuKhoaRequest.getTenKieuKhoa()).id(updateKieuKhoaRequest.getId()).build();
    }

    @Override
    public KieuKhoaResponse mappingByResponse(KieuKhoaEntity kieuKhoaEntity) {
        return KieuKhoaResponse.builder().tenKieuKhoa(kieuKhoaEntity.getTenKieuKhoa()).id(kieuKhoaEntity.getId()).build();
    }

    @Override
    public KieuKhoaResponse mappingResponseDetail(KieuKhoaEntity kieuKhoaEntity) {
        return null;
    }
}
