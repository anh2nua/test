package com.example.fashionmanager.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeleteRequest extends HistoryRequestDto{
    private Long id;
    private List<Long> ids;
    private Boolean deleted;
}
