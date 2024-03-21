package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.HistoryMethod;
import com.example.fashionmanager.enums.TenBangEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "lich_su_tuong_tac_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LichSuTuongTacEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVienEntity nhanVienEntity;

    @Column(name = "thao_tac")
    @Enumerated(EnumType.STRING)
    private HistoryMethod thaoTac;

    @Column(name = "mo_ta", columnDefinition = "LONGTEXT")
    private String moTa;
    @Column(name = "ten_bang")
    @Enumerated(EnumType.STRING)
    private TenBangEnum tenBang;

    @Column(name = "du_lieu_cu")
    private String duLieuCu;

    @Column(name = "du_lieu_moi")
    private String duLieuMoi;
}
