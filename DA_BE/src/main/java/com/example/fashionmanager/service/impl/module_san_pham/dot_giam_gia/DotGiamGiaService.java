package com.example.fashionmanager.service.impl.module_san_pham.dot_giam_gia;

import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaCreateRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaListRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaUpdateRequest;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response.DotGiamGiaReponse;
import com.example.fashionmanager.dto.admin.dotgiamgia.quanlydotgiamgia.response.DotGiamGiaResponseDetail;
import com.example.fashionmanager.entity.DotGiamGiaEntity;
import com.example.fashionmanager.service.CRUDService;

public interface DotGiamGiaService extends CRUDService<DotGiamGiaEntity, DotGiamGiaCreateRequest, DotGiamGiaUpdateRequest, DotGiamGiaListRequest
        , DotGiamGiaReponse, DotGiamGiaResponseDetail> {
}
