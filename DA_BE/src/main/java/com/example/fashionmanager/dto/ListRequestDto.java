package com.example.fashionmanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListRequestDto {
    private int page;

    private int size;

    private Boolean deleted;

    private Boolean active;
}
