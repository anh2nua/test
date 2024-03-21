package com.example.fashionmanager.service.impl.module_san_pham.tintuc;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlymausac.response.MauSacResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.request.TinTucUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlytintuc.response.TinTucResponse;
import com.example.fashionmanager.entity.MauSacEntity;
import com.example.fashionmanager.entity.TinTucEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.TinTucRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TinTucServiceImpl implements TinTucService{
    @Autowired
    private TinTucRepository tinTucRepository;
    //private String path = "F:\\lam btsv\\thang 3\\5.3\\DATN_PASS_FE_MANAGER\\FE_BAG_Manager-main\\public\\image\\";
    private String pathBE = "D:\\LINSU\\qltintuc\\DATN_PASS_FE_MANAGER-dev\\public\\image\\";
    private String pathFE = "D:\\LINSU\\qltintuc\\DATN-FE-SHOP-dev_fix_layout\\DATN-FE-SHOP-dev_fix_layout\\multimart-react-ecommerce-main\\public\\image\\";

    @Override
    public ResponseEntity<?> getList(TinTucListRequest tinTucListRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(tinTucListRequest.getPage(), tinTucListRequest.getSize(), sort);

        Specification<TinTucEntity> tinTucEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(tinTucListRequest.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + tinTucListRequest.getTitle() + "%"));
            }
            if (StringUtils.isNotBlank(tinTucListRequest.getIntroduction())) {
                predicates.add(criteriaBuilder.like(root.get("introduction"), "%" + tinTucListRequest.getIntroduction() + "%"));
            }
            if (StringUtils.isNotBlank(tinTucListRequest.getContent())) {
                predicates.add(criteriaBuilder.like(root.get("content"), "%" + tinTucListRequest.getContent() + "%"));
            }
            if (StringUtils.isNotBlank(tinTucListRequest.getImage())) {
                predicates.add(criteriaBuilder.like(root.get("image"), "%" + tinTucListRequest.getImage() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<TinTucEntity> tinTucEntities = tinTucRepository.findAll(tinTucEntitySpecification, pageable);
        List<TinTucResponse> tinTucResponses = tinTucEntities.stream().map(tinTucEntity -> mappingByResponse(tinTucEntity)).toList();
        ListReponseDto<TinTucResponse> listReponseDto = new ListReponseDto<TinTucResponse>();
        listReponseDto.setItems(tinTucResponses);
        listReponseDto.setHasNextPage(tinTucEntities.hasNext());
        listReponseDto.setHasPreviousPage(tinTucEntities.hasPrevious());
        listReponseDto.setPageCount(tinTucEntities.getTotalPages());
        listReponseDto.setPageSize(tinTucEntities.getSize());
        listReponseDto.setTotalItemCount(tinTucEntities.getTotalElements());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        TinTucEntity tinTucEntity = tinTucRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(tinTucEntity));
    }

    public String saveFile(MultipartFile file) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = formatter.format(date);
        String fileName = "product-" +
                formattedDate + "." + getFileExtension(file.getOriginalFilename());
        String rs = "/image/" + fileName;
        try
        {
            File dirBE = new File(pathBE);
            if (!dirBE.exists())
            {
                dirBE.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(pathBE + fileName));
            File dirFE = new File(pathFE);
            if (!dirFE.exists())
            {
                dirFE.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(pathFE + fileName));
            return rs;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkFile(MultipartFile file) {
        String extension = getFileExtension(file.getOriginalFilename());
        if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    @Override
    public ResponseEntity<?> create(TinTucCreateRequest tinTucCreateRequest) {
        String url = saveFile(tinTucCreateRequest.getFile());
        if(url != null){
            TinTucEntity tinTucEntity = mappingByCreateRequest(tinTucCreateRequest);
            tinTucEntity.setImage(url);
            tinTucRepository.save(tinTucEntity);
            return ResponseEntity.ok(mappingByResponse(tinTucEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> update(TinTucUpdateRequest tinTucUpdateRequest) {
        if (!tinTucRepository.existsById(tinTucUpdateRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        TinTucEntity tinTucEntity = tinTucRepository.findById(tinTucUpdateRequest.getId()).get();
        if(tinTucEntity != null){
            try{
                String pathBE = this.pathBE + tinTucEntity.getImage().replace("/","\\").substring(7);
                Files.delete(Paths.get(pathBE));
                String pathFE = this.pathFE + tinTucEntity.getImage().replace("/","\\").substring(7);
                Files.delete(Paths.get(pathFE));
            }catch (Exception e){
                e.printStackTrace();
            }
            String url = saveFile(tinTucUpdateRequest.getFile());
            if(url != null){
                TinTucEntity entity = mappingByUpdateRequest(tinTucUpdateRequest);
                entity.setImage(url);
                tinTucRepository.save(entity);
                return ResponseEntity.ok(mappingByResponse(entity));
            }
            throw new FashionManagerException(new ErrorResponse(HttpStatus.BAD_REQUEST, "Cập nhập thất bại"));
        }
        throw new FashionManagerException(new ErrorResponse(HttpStatus.BAD_REQUEST, "Cập nhập thất bại"));
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<TinTucEntity> tinTucEntities = tinTucRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(tinTucEntity -> tinTucEntity.setDeleted(deleteRequest.getDeleted())).toList();
            tinTucRepository.saveAll(tinTucEntities);
        } else {
            TinTucEntity tinTucEntity = tinTucRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            tinTucEntity.setDeleted(deleteRequest.getDeleted());
            tinTucRepository.save(tinTucEntity);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }

    @Override
    public TinTucEntity mappingByCreateRequest(TinTucCreateRequest tinTucCreateRequest) {
        return TinTucEntity.builder()
                .title(tinTucCreateRequest.getTitle())
                .content(tinTucCreateRequest.getContent())
                .postingDate(tinTucCreateRequest.getPostingDate())
                .dropDate(tinTucCreateRequest.getDropDate())
                .introduction(tinTucCreateRequest.getIntroduction())
                .build();
    }

    @Override
    public TinTucEntity mappingByUpdateRequest(TinTucUpdateRequest tinTucUpdateRequest) {
        return TinTucEntity.builder()
                .title(tinTucUpdateRequest.getTitle())
                .content(tinTucUpdateRequest.getContent())
                .postingDate(tinTucUpdateRequest.getPostingDate())
                .dropDate(tinTucUpdateRequest.getDropDate())
                .introduction(tinTucUpdateRequest.getIntroduction())
                .id(tinTucUpdateRequest.getId())
                .build();
    }

    @Override
    public TinTucResponse mappingByResponse(TinTucEntity tinTucEntity) {
        return TinTucResponse.builder()
                .title(tinTucEntity.getTitle())
                .content(tinTucEntity.getContent())
                .postingDate(tinTucEntity.getPostingDate())
                .dropDate(tinTucEntity.getDropDate())
                .image(tinTucEntity.getImage())
                .introduction(tinTucEntity.getIntroduction())
                .id(tinTucEntity.getId())
                .build();
    }

    @Override
    public TinTucResponse mappingResponseDetail(TinTucEntity tinTucEntity) {
        return TinTucResponse.builder()
                .title(tinTucEntity.getTitle())
                .content(tinTucEntity.getContent())
                .postingDate(tinTucEntity.getPostingDate())
                .dropDate(tinTucEntity.getDropDate())
                .image(tinTucEntity.getImage())
                .introduction(tinTucEntity.getIntroduction())
                .id(tinTucEntity.getId())
                .build();
    }
}
