package com.example.fashionmanager.entity.common;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class CommonEntityListener {
    @PrePersist
    private void prePersistCreate(CommonEntity entity) {

        entity.setDateCreate(new Date());
        entity.setDateUpdate(new Date());
        entity.setActive(true);
        entity.setDeleted(false);
    }

    @PreUpdate
    private void prePersistUpdate(CommonEntity entity) {

        entity.setDateUpdate(new Date());
    }
}
