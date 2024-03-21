package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tin_tuc_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TinTucEntity extends CommonEntity implements Serializable {
    @Column(name = "title")
    private String title;
    @Column(name = "postingdate")
    private Date postingDate;
    @Column(name = "dropdate")
    private Date dropDate;
    @Column(name = "image")
    private String image;
    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
