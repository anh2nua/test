package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "kich_thuoc_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KichThuocEntity extends CommonEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "kich_co_id")
    private KichCoEntity kichCoEntity;
    @Column(name = "chieu_dai")
    private Integer chieuDai;

    @Column(name = "chieu_rong")
    private Integer chieuRong;

    @Column(name = "chieu_cao")
    private Integer chieuCao;



}
