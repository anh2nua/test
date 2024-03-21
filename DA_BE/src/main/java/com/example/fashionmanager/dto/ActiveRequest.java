package com.example.fashionmanager.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActiveRequest {
    private Long id;
    private List<Long> ids;
    private Boolean actived;
}
