package com.example.fashionmanager.service.impl.module_san_pham.quanlybinhluan;

import com.example.fashionmanager.dto.DeleteRequest;
import com.example.fashionmanager.dto.shop.binhluan.request.ApproveRequest;
import com.example.fashionmanager.dto.shop.binhluan.request.BinhLuanCreateRequest;
import com.example.fashionmanager.dto.shop.binhluan.request.BinhLuanListRequest;
import com.example.fashionmanager.entity.BinhLuanEntity;
import com.example.fashionmanager.entity.ChiTietSanPhamEntity;
import com.example.fashionmanager.enums.TrangThaiBinhLuanEnum;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.BinhLuanRepository;
import com.example.fashionmanager.repository.ChiTietSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BinhLuanServiceImpl implements BinhLuanService {
    @Autowired
    private BinhLuanRepository binhLuanRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public ResponseEntity<?> createBinhLuan(BinhLuanCreateRequest request) {
        ChiTietSanPhamEntity chiTietSanPham = chiTietSanPhamRepository.findById(request.getChiTietSanPhamId())
                .orElseThrow(() -> new FashionManagerException(
                                new ErrorResponse(HttpStatus.NOT_FOUND,
                                        "Không tìm thấy sản phẩm có id = " + request.getChiTietSanPhamId())
                        )
                );
        BinhLuanEntity binhLuanEntity = BinhLuanEntity.builder()
                .trangThaiBinhLuan(TrangThaiBinhLuanEnum.CHO_PHE_DUYET)
                .chiTietSanPhamEntity(chiTietSanPham)
                .diemDanhGia(request.getDiemDanhGia())
                .hoTen(request.getHoTen())
                .email(request.getEmail())
                .sdt(request.getSdt())
                .noiDung(request.getNoiDung())
                .build();
        binhLuanRepository.save(binhLuanEntity);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> approveBinhLuan(ApproveRequest request) {
        BinhLuanEntity binhLuanEntity = binhLuanRepository.findById(request.getId())
                .orElseThrow(() -> new FashionManagerException(
                                new ErrorResponse(HttpStatus.NOT_FOUND,
                                        "Không tìm thấy bình luận có id = " + request.getId())
                        )
                );
        binhLuanEntity.setTrangThaiBinhLuan(request.getTrangThaiBinhLuan());
        binhLuanRepository.save(binhLuanEntity);
        return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBinhLuan(DeleteRequest request) {

        return null;
    }

    @Override
    public ResponseEntity<?> getListBinhLuan(BinhLuanListRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> detailBinhLuan(Long id) {
        return null;
    }
}
