package com.example.fashionmanager.service.impl.module_san_pham.chatlieudaydeo;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoCreateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoListRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.request.ChatLieuDayDeoUpdateRequest;
import com.example.fashionmanager.dto.admin.sanpham.quanlychatlieudaydeo.response.ChatLieuDayDeoResponse;
import com.example.fashionmanager.entity.ChatLieuDayDeoEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.ChatLieuDayDeoRepository;
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
public class ChatLieuDayDeoServiceImpl implements ChatLieuDayDeoService {
    @Autowired
    private ChatLieuDayDeoRepository chatLieuDayDeoRepository;

    @Override
    public ResponseEntity<?> getList(ChatLieuDayDeoListRequest chatLieuDayDeoListRequest) {
        Page<ChatLieuDayDeoEntity> chatLieuDayDeoEntities = chatLieuDayDeoRepository.findAllChatLieuDayDeo(chatLieuDayDeoListRequest.getTenChatLieuDayDeo() == null ?
                null : "%" + chatLieuDayDeoListRequest.getTenChatLieuDayDeo() + "%", PageRequest.of(chatLieuDayDeoListRequest.getPage(), chatLieuDayDeoListRequest.getSize()));
        ListReponseDto<ChatLieuDayDeoResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(chatLieuDayDeoEntities.stream().map(this::mappingByResponse).collect(Collectors.toList()));
        listReponseDto.setPageSize(chatLieuDayDeoEntities.getSize());
        listReponseDto.setPageCount(chatLieuDayDeoEntities.getTotalPages());
        listReponseDto.setPageIndex(chatLieuDayDeoListRequest.getPage());
        listReponseDto.setHasNextPage(chatLieuDayDeoEntities.hasNext());
        listReponseDto.setHasPreviousPage(chatLieuDayDeoEntities.hasPrevious());
        listReponseDto.setTotalItemCount(chatLieuDayDeoEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        ChatLieuDayDeoEntity chatLieuDayDeo = chatLieuDayDeoRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + id)));
        return ResponseEntity.ok(mappingByResponse(chatLieuDayDeo));
    }

    @Override
    public ResponseEntity<?> create(ChatLieuDayDeoCreateRequest chatLieuDayDeoCreateRequest) {
        ChatLieuDayDeoEntity chatLieuDayDeo = mappingByCreateRequest(chatLieuDayDeoCreateRequest);
        chatLieuDayDeoRepository.save(chatLieuDayDeo);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(ChatLieuDayDeoUpdateRequest chatLieuDayDeoUpdateRequest) {
        if (chatLieuDayDeoRepository.existsById(chatLieuDayDeoUpdateRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + chatLieuDayDeoUpdateRequest.getId()));
        }
        ChatLieuDayDeoEntity chatLieuDayDeo = mappingByUpdateRequest(chatLieuDayDeoUpdateRequest);
        chatLieuDayDeoRepository.save(chatLieuDayDeo);
        return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<ChatLieuDayDeoEntity> chatLieuDayDeoEntities = chatLieuDayDeoRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(chatLieuDayDeoEntity -> chatLieuDayDeoEntity.setDeleted(deleteRequest.getDeleted())).toList();
            chatLieuDayDeoRepository.saveAll(chatLieuDayDeoEntities);
        } else {
            ChatLieuDayDeoEntity chatLieuDayDeo = chatLieuDayDeoRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            chatLieuDayDeo.setDeleted(deleteRequest.getDeleted());
            chatLieuDayDeoRepository.save(chatLieuDayDeo);

        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        return null;
    }


    @Override
    public ChatLieuDayDeoEntity mappingByCreateRequest(ChatLieuDayDeoCreateRequest chatLieuDayDeoCreateRequest) {
        return ChatLieuDayDeoEntity.builder().tenChatLieuDayDeo(chatLieuDayDeoCreateRequest.getTenChatLieuDayDeo()).build();
    }

    @Override
    public ChatLieuDayDeoEntity mappingByUpdateRequest(ChatLieuDayDeoUpdateRequest chatLieuDayDeoUpdateRequest) {
        return ChatLieuDayDeoEntity.builder().id(chatLieuDayDeoUpdateRequest.getId()).tenChatLieuDayDeo(chatLieuDayDeoUpdateRequest.getTenChatLieuDayDeo()).build();
    }

    @Override
    public ChatLieuDayDeoResponse mappingByResponse(ChatLieuDayDeoEntity chatLieuDayDeoEntity) {
        return ChatLieuDayDeoResponse.builder().id(chatLieuDayDeoEntity.getId()).tenChatLieuDayDeo(chatLieuDayDeoEntity.getTenChatLieuDayDeo()).build();
    }

    @Override
    public ChatLieuDayDeoResponse mappingResponseDetail(ChatLieuDayDeoEntity chatLieuDayDeoEntity) {
        return null;
    }
}
