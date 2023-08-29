package com.cej.toy.test.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class User implements UserDetails {
    private String num;
    private String id;
    private String password;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role)); // 권한 반환
    }

    @Override
    public String getPassword() {
        return password;
    }

    // 고유한 값 반환
    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료 X
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠금 X
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료 X
    }

    @Override
    public boolean isEnabled() {
        return true; // 사용가능
    }
}
