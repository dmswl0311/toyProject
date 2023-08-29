package com.cej.toy.test.config;

import com.cej.toy.test.service.TestService;
import com.cej.toy.test.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http
                .csrf(AbstractHttpConfigurer::disable) //CSRF 공격에 대한 방어 해제
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/login","/testApi/login","/testApi/join","/testApi/logout").permitAll()
                                .requestMatchers("/**").authenticated() // 로그인 한 사용자 접근 가능
                )
                .formLogin((login) ->
                        login.loginProcessingUrl("/testApi/login")
                                .usernameParameter("id")
                                .passwordParameter("password")
                                ) // formLogin
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(true)
                                .logoutUrl("/testApi/logout")
                                .logoutSuccessUrl("/login"))
                .httpBasic(withDefaults())
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
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
