package com.cej.toy.test.filter;

import com.cej.toy.test.config.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter를 적용함으로써 servlet에 도달하기 전에 검증을 완료할 수 있다.
 * OncePerRequestFilter는 단 한번의 요청에 단 한번만 동작하도록 보장된 필터다.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.getTokentoHeader(request);
        if (token != null && jwtProvider.verifyToken(token)) {
            // 인증된 유저정보 가져오기(BARER떼고 토큰만 전달)
            Authentication authentication = jwtProvider.getUserByToken(token.split(" ")[1]);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
