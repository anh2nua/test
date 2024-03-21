package com.example.fashionmanager.service.impl.module_nhan_vien.quanlynhanvien;

import com.example.fashionmanager.dto.ActiveRequest;
import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.admin.nhanvien.request.ChangePasswordRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienCreateRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienListRequest;
import com.example.fashionmanager.dto.admin.nhanvien.request.NhanVienUpdateRequest;
import com.example.fashionmanager.dto.admin.nhanvien.response.NhanVienDetailResponse;
import com.example.fashionmanager.dto.admin.nhanvien.response.NhanVienResponse;
import com.example.fashionmanager.entity.*;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.NhanVienRepository;
import com.example.fashionmanager.repository.RoleRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.UserRoleRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public ResponseEntity<?> getList(NhanVienListRequest nhanVienListRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(nhanVienListRequest.getPage(), nhanVienListRequest.getSize(), sort);
        Specification<NhanVienEntity> nhanVienEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(nhanVienListRequest.getTenNhanVien())) {
                predicates.add(criteriaBuilder.like(root.get("tenNhanVien"), "%" + nhanVienListRequest.getTenNhanVien() + "%"));
            }
            if (StringUtils.isNotBlank(nhanVienListRequest.getSoDienThoai())) {
                predicates.add(criteriaBuilder.like(root.get("soDienThoai"), "%" + nhanVienListRequest.getSoDienThoai() + "%"));
            }
            if (Objects.nonNull(nhanVienListRequest.getGioiTinh())) {
                predicates.add(criteriaBuilder.equal(root.get("gioiTinh"), nhanVienListRequest.getGioiTinh()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<NhanVienEntity> nhanVienEntities = nhanVienRepository.findAll(nhanVienEntitySpecification, pageable);
        ListReponseDto<NhanVienResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(nhanVienEntities.stream().map(this::mappingByResponse).toList());
        listReponseDto.setPageIndex(nhanVienListRequest.getPage());
        listReponseDto.setPageSize(nhanVienListRequest.getSize());
        listReponseDto.setPageCount(nhanVienEntities.getTotalPages());
        listReponseDto.setHasPreviousPage(nhanVienEntities.hasPrevious());
        listReponseDto.setHasNextPage(nhanVienEntities.hasNext());
        listReponseDto.setTotalItemCount(nhanVienEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        NhanVienEntity nhanVienEntity = nhanVienRepository.findById(id).orElseThrow(() ->
                new FashionManagerException(
                        new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy nhân viên có id = " + id)));
        return ResponseEntity.ok(mappingResponseDetail(nhanVienEntity));
    }

    @Override
    public ResponseEntity<?> create(NhanVienCreateRequest nhanVienCreateRequest) {
        NhanVienEntity nhanVienEntity = mappingByCreateRequest(nhanVienCreateRequest);
        nhanVienRepository.save(nhanVienEntity);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(NhanVienUpdateRequest nhanVienUpdateRequest) {
        if (!nhanVienRepository.existsById(nhanVienUpdateRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy nhân viên có id = " + nhanVienUpdateRequest.getId()));
        }
        NhanVienEntity nhanVienEntity = mappingByUpdateRequest(nhanVienUpdateRequest);
        nhanVienRepository.save(nhanVienEntity);
        return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(DeleteRequest deleteRequest) {
        if (Objects.nonNull(deleteRequest.getIds()) && !deleteRequest.getIds().isEmpty()) {
            List<NhanVienEntity> nhanVienEntities = nhanVienRepository.findAllById(deleteRequest.getIds())
                    .stream().peek(nhanVienEntity -> nhanVienEntity.setDeleted(deleteRequest.getDeleted())).toList();
            nhanVienRepository.saveAll(nhanVienEntities);
        } else {
            NhanVienEntity nhanVienEntity = nhanVienRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + deleteRequest.getId())));
            nhanVienEntity.setDeleted(deleteRequest.getDeleted());
            nhanVienRepository.save(nhanVienEntity);

        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeActive(ActiveRequest activeRequest) {
        if (Objects.nonNull(activeRequest.getIds()) && !activeRequest.getIds().isEmpty()) {
            List<NhanVienEntity> nhanVienEntities = nhanVienRepository.findAllById(activeRequest.getIds())
                    .stream().peek(nhanVienEntity -> nhanVienEntity.setActive(activeRequest.getActived())).toList();
            nhanVienRepository.saveAll(nhanVienEntities);
        } else {
            NhanVienEntity nhanVienEntity = nhanVienRepository.findById(activeRequest.getId())
                    .orElseThrow(() -> new FashionManagerException(
                            new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy id = " + activeRequest.getId())));
            nhanVienEntity.setActive(activeRequest.getActived());
            nhanVienRepository.save(nhanVienEntity);
        }
        return new ResponseEntity<>(activeRequest.getActived() ? "ACTIVED" : "INACTIVED", HttpStatus.OK);
    }


    @Override
    public NhanVienEntity mappingByCreateRequest(NhanVienCreateRequest nhanVienCreateRequest) {
        if (userRepository.findByUserName(nhanVienCreateRequest.getTenNhanVien()).isPresent() || userRepository.findByEmail(nhanVienCreateRequest.getEmail()).isPresent()) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Tài khoản đã tồn tại"));
        }
        if (nhanVienRepository.findByCccd(nhanVienCreateRequest.getCccd()).isPresent()) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Căn cước đã tồn tại"));
        }
        UserEntity userEntity = UserEntity.builder()
                .userName(nhanVienCreateRequest.getTenDangNhap())
                .email(nhanVienCreateRequest.getEmail())
                .password(passwordEncoder.encode(nhanVienCreateRequest.getMatKhau()))
                .build();

        List<RoleEntity> roleEntities = roleRepository.findAllByRoleNameInAndActiveIsTrueAndDeletedIsFalse(nhanVienCreateRequest.getChucVu());
        if (!roleEntities.isEmpty()) {
            Set<UserRoleEntity> userRoleEntities = roleEntities
                    .stream()
                    .map(roleEntity -> UserRoleEntity.builder().userEntity(userEntity)
                            .roleEntity(roleEntity).build())
                    .collect(Collectors.toSet());
            userEntity.setUserRoleEntities(userRoleEntities);
        }
        userRepository.save(userEntity);

        return NhanVienEntity.builder()
                .tenNhanVien(nhanVienCreateRequest.getTenNhanVien())
                .diaChi(nhanVienCreateRequest.getDiaChi())
                .cccd(nhanVienCreateRequest.getCccd())
                .gioiTinh(nhanVienCreateRequest.getGioiTinh())
                .ngaySinh(nhanVienCreateRequest.getNgaySinh())
                .soDienThoai(nhanVienCreateRequest.getSoDienThoai())
                .anhUrl(nhanVienCreateRequest.getAnhUrl())
                .userEntity(userEntity)
                .build();
    }

    @Override

    public NhanVienEntity mappingByUpdateRequest(NhanVienUpdateRequest nhanVienUpdateRequest) {
        if (nhanVienRepository.findByCccdAndIdNot(nhanVienUpdateRequest.getCccd(), nhanVienUpdateRequest.getId()).isPresent()) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Căn cước đã tồn tại"));
        }
        UserEntity userEntity = userRepository.findByUserName(nhanVienUpdateRequest.getTenDangNhap()).orElseThrow(() -> new FashionManagerException(
                new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng: " + nhanVienUpdateRequest.getTenDangNhap())));

        userRoleRepository.deleteAllByUserEntity(userEntity);

        List<RoleEntity> roleEntities = roleRepository.findAllByRoleNameInAndActiveIsTrueAndDeletedIsFalse(nhanVienUpdateRequest.getChucVu());

        if (!roleEntities.isEmpty()) {
            Set<UserRoleEntity> userRoleEntities = roleEntities
                    .stream()
                    .map(roleEntity -> UserRoleEntity.builder().userEntity(userEntity)
                            .roleEntity(roleEntity).build())
                    .collect(Collectors.toSet());
            userEntity.setUserRoleEntities(userRoleEntities);
        }
        userRepository.save(userEntity);
        return NhanVienEntity.builder()
                .tenNhanVien(nhanVienUpdateRequest.getTenNhanVien())
                .diaChi(nhanVienUpdateRequest.getDiaChi())
                .cccd(nhanVienUpdateRequest.getCccd())
                .gioiTinh(nhanVienUpdateRequest.getGioiTinh())
                .ngaySinh(nhanVienUpdateRequest.getNgaySinh())
                .soDienThoai(nhanVienUpdateRequest.getSoDienThoai())
                .anhUrl(nhanVienUpdateRequest.getAnhUrl())
                .userEntity(userEntity)
                .id(nhanVienUpdateRequest.getId())
                .build();
    }

    @Override
    public NhanVienResponse mappingByResponse(NhanVienEntity nhanVienEntity) {
        return NhanVienResponse.builder()
                .id(nhanVienEntity.getId())
                .tenNhanVien(nhanVienEntity.getTenNhanVien())
                .anhUrl(nhanVienEntity.getAnhUrl())
                .soDienThoai(nhanVienEntity.getSoDienThoai())
                .gioiTinh(nhanVienEntity.getGioiTinh())
                .build();
    }

    @Override
    public NhanVienDetailResponse mappingResponseDetail(NhanVienEntity nhanVienEntity) {
        return NhanVienDetailResponse.builder()
                .tenNhanVien(nhanVienEntity.getTenNhanVien())
                .anhUrl(nhanVienEntity.getAnhUrl())
                .soDienThoai(nhanVienEntity.getSoDienThoai())
                .gioiTinh(nhanVienEntity.getGioiTinh())
                .tenDangNhap(nhanVienEntity.getUserEntity().getUserName())
                .email(nhanVienEntity.getUserEntity().getEmail())
                .cccd(nhanVienEntity.getCccd())
                .diaChi(nhanVienEntity.getDiaChi())
                .ngaySinh(nhanVienEntity.getNgaySinh())
                .id(nhanVienEntity.getId())
                .chucVu(nhanVienEntity.getUserEntity().getUserRoleEntities()
                        .stream()
                        .map(UserRoleEntity::getRoleEntity)
                        .map(RoleEntity::getRoleName)
                        .collect(Collectors.toList()))
                .build();
    }


    @Override
    public ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest) {
        NhanVienEntity nhanVienEntity = nhanVienRepository.findById(changePasswordRequest.getId())
                .orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy nhân viên c id = " + changePasswordRequest.getId())));
        UserEntity userEntity = nhanVienEntity.getUserEntity();
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), userEntity.getPassword())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.BAD_REQUEST, "Mật khẩu cũ không đúng,vui lòng kiểm tra lại"));
        }
        userEntity.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(userEntity);
        return ResponseEntity.ok("CHANGE_PASSWORD_SUCCESS");
    }
}
