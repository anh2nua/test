package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import org.springframework.http.ResponseEntity;

public interface CRUDService<Entity, CreateRequest, UpdateRequest, ListRequest, Response, ResponseDetail> {
    ResponseEntity<?> getList(ListRequest listRequest);

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> create(CreateRequest createRequest);

    ResponseEntity<?> update(UpdateRequest updateRequest);
    ResponseEntity<?> delete(DeleteRequest deleteRequest);
    ResponseEntity<?> changeActive(ActiveRequest activeRequest);

    Entity mappingByCreateRequest(CreateRequest createRequest);
    Entity mappingByUpdateRequest(UpdateRequest updateRequest);

    Response mappingByResponse(Entity entity);
    ResponseDetail mappingResponseDetail(Entity entity);
}
