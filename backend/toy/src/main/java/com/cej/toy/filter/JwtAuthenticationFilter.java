package com.cej.toy.filter;

import com.cej.toy.config.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter를 적용함으로써 servlet에 도달하기 전에 검증을 완료할 수 있다.
 * OncePerRequestFilter는 단 한번의 요청에 단 한번만 동작하도록 보장된 필터다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.getTokentoHeader(request);
        if (log.isDebugEnabled()) {
            log.info("token :: "+token);
        }
        if (token != null && jwtProvider.verifyToken(token)) {
            // 유효한 토큰
            // redis에 저장되어 있는지 확인 (로그아웃 확인)
            String key = "JWT_TOKEN:" + jwtProvider.getUserId(token.split(" ")[1]);
            String storedToken = (String) redisTemplate.opsForValue().get(key);

            // 로그아웃 여부 판단
            if(!ObjectUtils.isEmpty(storedToken)) {
                // 로그아웃 X
                // 인증된 유저정보 가져오기 (BARER떼고 토큰만 전달)
                Authentication authentication = jwtProvider.getUserByToken(token.split(" ")[1]);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.info("login X");
            }
        }
        filterChain.doFilter(request, response);

    }
}
