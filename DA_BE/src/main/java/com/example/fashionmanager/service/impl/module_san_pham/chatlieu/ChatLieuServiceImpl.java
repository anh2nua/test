package com.example.fashionmanager.service.impl.module_san_pham.chatlieu;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request.CreateChatLieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request.ListChatLieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.request.UpdateChatLieuRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.response.ChatLieuDetailResponse;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieu.response.ChatLieuResponse;
import com.example.fashionmanager.entity.ChatLieuEntity;
import com.example.fashionmanager.entity.LichSuTuongTacEntity;
import com.example.fashionmanager.entity.NhanVienEntity;
import com.example.fashionmanager.enums.HistoryMethod;
import com.example.fashionmanager.enums.TenBangEnum;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.ChatLieuRepository;
import com.example.fashionmanager.repository.LichSutuongTacRepository;
import com.example.fashionmanager.repository.NhanVienRepository;
import com.example.fashionmanager.util.HistoryUtils;
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
public class ChatLieuServiceImpl implements ChatLieuService {
    @Autowired
    private ChatLieuRepository chatLieuRepository;
    @Autowired
    private HistoryUtils historyUtils;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public ResponseEntity<ListReponseDto<ChatLieuResponse>> getList(ListChatLieuRequest listChatLieuRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(listChatLieuRequest.getPage(), listChatLieuRequest.getSize(), sort);
        Specification<ChatLieuEntity> chatLieuEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listChatLieuRequest.getTenChatLieu())) {
                predicates.add(criteriaBuilder.like(root.get("tenChatLieu"), "%" + listChatLieuRequest.getTenChatLieu() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<ChatLieuEntity> chatLieuEntities = chatLieuRepository.findAll(chatLieuEntitySpecification, pageable);
        List<ChatLieuResponse> chatLieuResponses = chatLieuEntities.stream().map(chatLieuEntity -> mappingByResponse(chatLieuEntity)).toList();
        ListReponseDto<ChatLieuResponse> listReponseDto = new ListReponseDto<ChatLieuResponse>();
        listReponseDto.setItems(chatLieuResponses);
        listReponseDto.setHasNextPage(chatLieuEntities.hasNext());
        listReponseDto.setHasPreviousPage(chatLieuEntities.hasPrevious());
        listReponseDto.setPageCount(chatLieuEntities.getTotalPages());
        listReponseDto.setPageSize(chatLieuEntities.getSize());
        listReponseDto.setTotalItemCount(chatLieuEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    ;

    @Override
    public ResponseEntity<?> getById(Long id) {
        ChatLieuEntity chatLieuEntity = chatLieuRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(chatLieuEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateChatLieuRequest createChatLieuRequest) {
        ChatLieuEntity chatLieuEntity = mappingByCreateRequest(createChatLieuRequest);
        chatLieuRepository.save(chatLieuEntity);

        //tạo lịch sử tương tác
        historyUtils.createLichSuTuongTac(
                historyUtils.getNhanVienEntity(),
                HistoryMethod.CREATE,
                null,
                chatLieuEntity,
                TenBangEnum.CHAT_LIEU,
                createChatLieuRequest.getMoTaTuongTac());
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> update(UpdateChatLieuRequest updateChatLieuRequest) {
        ChatLieuEntity chatLieuEntityCu = chatLieuRepository.findById(updateChatLieuRequest.getId())
                .orElseThrow(() ->
                        new FashionManagerException(
                                new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")
                        )
                );
        ChatLieuEntity chatLieuEntityMoi = chatLieuRepository.save(mappingByUpdateRequest(updateChatLieuRequest));
        //tạo lịch sử tương tác

        historyUtils.createLichSuTuongTac(
                historyUtils.getNhanVienEntity(),
                HistoryMethod.UPDATE,
                chatLieuEntityCu,
                chatLieuEntityMoi,
                TenBangEnum.CHAT_LIEU,
                updateChatLieuRequest.getMoTaTuongTac());
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<ChatLieuEntity> chatLieuEntities = chatLieuRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(chatLieuEntity -> chatLieuEntity.setDeleted(deleteRequest.getDeleted())).toList();
            chatLieuRepository.saveAll(chatLieuEntities);
            chatLieuEntities.forEach(chatLieuEntity -> historyUtils.createLichSuTuongTac(
                    historyUtils.getNhanVienEntity(),
                    deleteRequest.getDeleted() ? HistoryMethod.DELETE : HistoryMethod.RESTORE,
                    null,
                    chatLieuEntity,
                    TenBangEnum.CHAT_LIEU,
                    deleteRequest.getMoTaTuongTac()
            ));
        } else {
            ChatLieuEntity chatLieuEntity = chatLieuRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            chatLieuEntity.setDeleted(deleteRequest.getDeleted());
            chatLieuRepository.save(chatLieuEntity);
            historyUtils.createLichSuTuongTac(
                    historyUtils.getNhanVienEntity(),
                    deleteRequest.getDeleted() ? HistoryMethod.DELETE : HistoryMethod.RESTORE,
                    null,
                    chatLieuEntity,
                    TenBangEnum.CHAT_LIEU,
                    deleteRequest.getMoTaTuongTac()
            );
        }
        return new ResponseEntity<>(deleteRequest.getDeleted() ? "DELETED" : "RETORED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }


    @Override
    public ChatLieuEntity mappingByCreateRequest(CreateChatLieuRequest createChatLieuRequest) {
        return ChatLieuEntity.builder()
                .tenChatLieu(createChatLieuRequest.getTenChatLieu())
                .moTa(createChatLieuRequest.getMoTa()).build();
    }

    @Override
    public ChatLieuEntity mappingByUpdateRequest(UpdateChatLieuRequest updateChatLieuRequest) {
        return ChatLieuEntity.builder()
                .tenChatLieu(updateChatLieuRequest.getTenChatLieu())
                .moTa(updateChatLieuRequest.getMoTa())
                .id(updateChatLieuRequest.getId())
                .build();
    }

    @Override
    public ChatLieuResponse mappingByResponse(ChatLieuEntity chatLieuEntity) {
        return ChatLieuResponse.builder()
                .id(chatLieuEntity.getId())
                .tenChatLieu(chatLieuEntity.getTenChatLieu())
                .build();
    }

    @Override
    public ChatLieuDetailResponse mappingResponseDetail(ChatLieuEntity chatLieuEntity) {
        return ChatLieuDetailResponse.builder()
                .id(chatLieuEntity.getId())
                .tenChatLieu(chatLieuEntity.getTenChatLieu())
                .moTa(chatLieuEntity.getMoTa())
                .build();
    }
}
