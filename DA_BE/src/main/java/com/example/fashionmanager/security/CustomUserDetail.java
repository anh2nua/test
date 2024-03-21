package com.example.fashionmanager.security;

import com.example.fashionmanager.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@NoArgsConstructor
public class CustomUserDetail implements UserDetails {

    private String userName;

    private String email;

    private String password;

    private List<GrantedAuthority> authorities;

    public CustomUserDetail(UserEntity userEntity) {
        userName = userEntity.getUserName();
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        authorities = userEntity.getUserRoleEntities().stream()
                .map(o -> o.getRoleEntity().getRoleName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
