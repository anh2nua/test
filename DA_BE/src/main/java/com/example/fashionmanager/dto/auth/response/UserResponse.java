package com.example.fashionmanager.dto.auth.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
private String userName;
private String email;
private List<String> roles;
}
