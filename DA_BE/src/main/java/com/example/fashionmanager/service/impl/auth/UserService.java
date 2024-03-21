package com.example.fashionmanager.service.impl.auth;

import com.example.fashionmanager.dto.auth.request.LoginRequest;
import com.example.fashionmanager.dto.auth.response.LoginResponse;
import com.example.fashionmanager.dto.auth.response.UserResponse;
import com.example.fashionmanager.dto.admin.user.request.UserCreateRequest;
import com.example.fashionmanager.entity.RefreshToken;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import com.example.fashionmanager.repository.RoleRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.UserRoleRepository;
import com.example.fashionmanager.security.CustomUserDetail;
import com.example.fashionmanager.jwt.IJwtService;
import com.example.fashionmanager.service.IUserService;
import com.example.fashionmanager.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    IJwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Override
    public LoginResponse authenticate(LoginRequest request) throws Exception {
//        final var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
//        CustomUserDetail userDetails = userRepository
//                .findByUserName(request.getUserName())
//                .map(CustomUserDetail::new)
//                .orElseThrow(() -> new UsernameNotFoundException("Not found user name: " + request.getUserName()));
//        String token = getToken(userDetails);
//        return LoginResponse.builder().userResponse( UserResponse.builder()
//                .userName(userDetails.getUsername())
//                .email(userDetails.getEmail())
//                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .build()).token(token).build();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        String jwt = getToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return LoginResponse.builder().userResponse( UserResponse.builder()
                .userName(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build()).token(jwt).refreshToken(refreshToken.getToken()).build();
    }


    @Override
    public void add(UserEntity userEntity, UserRoleEntity userRoleEntity) {
        // Lưu UserEntity vào cơ sở dữ liệu
        userRepository.save(userEntity);

        // Lưu UserRoleEntity vào cơ sở dữ liệu
        userRoleRepository.save(userRoleEntity);
    }

    public String getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final var username = userDetails.getUsername();
        return jwtService.generateAccessToken(Map.of("role", roles), username);
    }

    private UserEntity convertEntity(UserCreateRequest userRequest){
        return UserEntity.builder()
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .userRoleEntities(userRequest.getUserRoleEntities())
                .build();
    }
}



