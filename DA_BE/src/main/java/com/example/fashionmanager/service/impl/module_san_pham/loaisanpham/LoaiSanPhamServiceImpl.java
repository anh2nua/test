package com.example.fashionmanager.service.impl.module_san_pham.loaisanpham;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.request.LoaiSanPhamUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlyloaisanpham.response.LoaiSanPhamResponse;
import com.example.fashionmanager.entity.LoaiSanPhamEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.LoaiSanPhamRepository;
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
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;

    @Override
    public ResponseEntity<?> getList(LoaiSanPhamListRequest loaiSanPhamListRequest) {
        Page<LoaiSanPhamEntity> loaiSanPhamEntities = loaiSanPhamRepository.findAllLoaiSanPham(loaiSanPhamListRequest.getTenLoaiSanPham() == null ?
                null : "%" + loaiSanPhamListRequest.getTenLoaiSanPham() + "%", PageRequest.of(loaiSanPhamListRequest.getPage(), loaiSanPhamListRequest.getSize()));
        ListReponseDto<LoaiSanPhamResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(loaiSanPhamEntities.stream().map(this::mappingByResponse).collect(Collectors.toList()));
        listReponseDto.setPageSize(loaiSanPhamEntities.getSize());
        listReponseDto.setPageCount(loaiSanPhamEntities.getTotalPages());
        listReponseDto.setPageIndex(loaiSanPhamListRequest.getPage());
        listReponseDto.setHasNextPage(loaiSanPhamEntities.hasNext());
        listReponseDto.setHasPreviousPage(loaiSanPhamEntities.hasPrevious());
        listReponseDto.setTotalItemCount(loaiSanPhamEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        LoaiSanPhamEntity loaiSanPhamEntity = loaiSanPhamRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thaays id = " + id)));
        return new ResponseEntity<>(mappingByResponse(loaiSanPhamEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(LoaiSanPhamCreateRequest loaiSanPhamCreateRequest) {
        LoaiSanPhamEntity loaiSanPhamEntity = mappingByCreateRequest(loaiSanPhamCreateRequest);
        loaiSanPhamRepository.save(loaiSanPhamEntity);
        return new ResponseEntity<>("CREATED", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> update(LoaiSanPhamUpdateRequest loaiSanPhamUpdateRequest) {
        if (loaiSanPhamRepository.existsById(loaiSanPhamUpdateRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thaays id = " + loaiSanPhamUpdateRequest.getId()));
        }
        LoaiSanPhamEntity loaiSanPhamEntity = mappingByUpdateRequest(loaiSanPhamUpdateRequest);
        loaiSanPhamRepository.save(loaiSanPhamEntity);
        return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<LoaiSanPhamEntity> loaiSanPhamEntities = loaiSanPhamRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(kieuKhoaEntity -> kieuKhoaEntity.setDeleted(deleteRequest.getDeleted())).toList();
            loaiSanPhamRepository.saveAll(loaiSanPhamEntities);
        } else {
            LoaiSanPhamEntity loaiSanPhamEntity = loaiSanPhamRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            loaiSanPhamEntity.setDeleted(deleteRequest.getDeleted());
            loaiSanPhamRepository.save(loaiSanPhamEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }





    @Override
    public LoaiSanPhamEntity mappingByCreateRequest(LoaiSanPhamCreateRequest loaiSanPhamCreateRequest) {
        return LoaiSanPhamEntity.builder().tenLoai(loaiSanPhamCreateRequest.getTenLoaiSanPham()).build();
    }

    @Override
    public LoaiSanPhamEntity mappingByUpdateRequest(LoaiSanPhamUpdateRequest loaiSanPhamUpdateRequest) {
        return LoaiSanPhamEntity.builder().id(loaiSanPhamUpdateRequest.getId()).tenLoai(loaiSanPhamUpdateRequest.getTenLoaiSanPham()).build();
    }

    @Override
    public LoaiSanPhamResponse mappingByResponse(LoaiSanPhamEntity loaiSanPhamEntity) {
        return LoaiSanPhamResponse.builder().tenLoaiSanPham(loaiSanPhamEntity.getTenLoai()).id(loaiSanPhamEntity.getId()).build();
    }

    @Override
    public LoaiSanPhamResponse mappingResponseDetail(LoaiSanPhamEntity loaiSanPhamEntity) {
        return null;
    }
}
