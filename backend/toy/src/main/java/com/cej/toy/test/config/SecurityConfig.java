package com.cej.toy.test.config;

import com.cej.toy.test.filter.JwtAuthenticationFilter;
import com.cej.toy.test.service.UserDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final UserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http
                .csrf(AbstractHttpConfigurer::disable) //CSRF 공격에 대한 방어 해제
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/login", "/testApi/login", "/testApi/join", "/testApi/logout", "/testApi/testPwd").permitAll()
                                .requestMatchers("/testApi/getUser").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/testApi/admin/**").hasRole("ADMIN")
                                .requestMatchers("/**").authenticated() // 로그인 한 사용자 접근 가능
                )
//                .formLogin((login) ->
//                        login.loginProcessingUrl("/testApi/login")
//                                .usernameParameter("id")
//                                .passwordParameter("password")
//                ) // formLogin
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(true)
                                .logoutUrl("/testApi/logout"))
                .httpBasic(AbstractHttpConfigurer::disable)
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedHandler(new AccessDeniedHandler() {
                                    @Override
                                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                                        // 권한 문제가 발생했을 때 이 부분을 호출한다.

                                        response.setStatus(403);
                                        response.setContentType("text/html; charset=UTF-8");
                                        response.getWriter().write("권한이 없는 사용자입니다.");
                                        log.error("Forbidden!!! message : "+ accessDeniedException.getMessage());
                                    }
                                })
                                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                                    @Override
                                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                                        // 인증문제가 발생했을 때 이 부분을 호출한다.
                                        response.setStatus(401);
                                        response.setContentType("text/html; charset=UTF-8");
                                        response.getWriter().write("인증되지 않은 사용자입니다.");
                                        log.error("Forbidden!!! message : "+ authException.getMessage());
                                    }
                                })
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

}
