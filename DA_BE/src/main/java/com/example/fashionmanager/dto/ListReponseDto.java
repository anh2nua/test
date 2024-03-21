package com.example.fashionmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListReponseDto<T> {
    private Integer pageSize;
    private List<T> items;
    private Integer pageIndex;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    private Integer pageCount;
    private Long totalItemCount;
}
