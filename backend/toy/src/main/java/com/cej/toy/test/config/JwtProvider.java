package com.cej.toy.test.config;

import com.cej.toy.test.service.UserDetailService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("${jwt.secret}") String secret;
    static final Long WEEK_EXP = 1000L * 60 * 60 * 24 * 7; //1주
    static final Long EXP = 1000L * 60 * 60; //1시간
    static final Long MIN_EXP = 1000L * 60 * 20; //20분
    static final Long TEST_EXP = 1000L * 10; //10초
    static final String TOKEN_TYPE = "Bearer";

    private final UserDetailService userDetailService;

    /**
     * 토큰 생성
     * @return
     */
    public String createToken(String id){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXP))
                .claim("id",id)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 토큰 검증
     * @return
     */
    public Boolean verifyToken(String token){
        // BEARER 검증
        if (TOKEN_TYPE.equals(token.substring(0, TOKEN_TYPE.length()))) {
            String credentials = token.split(" ")[1];
            Jws <Claims> parseClaimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(credentials);
            /**
             * 토큰 만료여부 검증
             * FALSE: 토큰 만료
             */
            return !parseClaimsJws.getBody().getExpiration().before(new Date());
        }
        return Boolean.FALSE;
    }

    public long getExpiration(String credentials) {
        Jws <Claims> parseClaimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(credentials);
        return parseClaimsJws.getBody().getExpiration().getTime();
    }

    /**
     * 토큰에 담겨있는 id 획득
     * @param credentials
     * @return
     */
    public String getUserId(String credentials) {
        Jws <Claims> parseClaimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(credentials);
        return (String) parseClaimsJws.getBody().get("id");
    }

    /**
     * 토큰으로 User 불러오기
     * @param token
     * @return
     */
    public Authentication getUserByToken(String token) {
        UserDetails userDetails = userDetailService.loadUserByUsername(this.getUserId(token));
        // 인증이 된 Authentication객체 만들기
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 헤더에서 토큰 가져오기
     * @param request
     * @return
     */
    public String getTokentoHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * 리프레시 토큰 생성
     */
    public String createRefreshToken(String id) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + WEEK_EXP))
                .claim("id",id)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


}
