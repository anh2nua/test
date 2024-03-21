package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "danh_muc_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DanhMucEntity extends CommonEntity implements Serializable {
    @Column(name = "ma_danh_muc")
    private String maDanhMuc;
    @Column(name = "ten_danh_muc")
    private String tenDanhMuc;
    @Column(name = "is_parent")
    private Boolean isParent;

    @ManyToOne
    @JoinColumn(name = "danh_muc_id")
    private DanhMucEntity danhMucEntity;

    @OneToMany(mappedBy = "danhMucEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DanhMucEntity> danhMucEntities = new HashSet<>();
}
