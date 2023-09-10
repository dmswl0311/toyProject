package com.cej.toy.test.service;

import com.cej.toy.config.JwtProvider;
import com.cej.toy.test.domain.dto.LoginResDto;
import com.cej.toy.test.domain.dto.TestDto;
import com.cej.toy.test.domain.dto.User;
import com.cej.toy.test.repository.TestRepository;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TestRepository testRepository;
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${jwt.secret}") String secret;

    private final JwtProvider jwtProvider;

    public List<String> getTestPwd(){
        List<TestDto> testDtoList = testRepository.selectTestDto();
        if (testDtoList != null && !testDtoList.isEmpty()) {
            return testDtoList
                    .stream()
                    .map(TestDto::getPassword)
                    .toList();
        }
        return null;
    }

    public String getToken(){
        return makeJWT();
    }

    public String makeJWT(){

        Date now = new Date();

        log.info("secret :: "+secret);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("num","회원 번호")
                .claim("id", "아이디") // (5)
                .signWith(SignatureAlgorithm.HS256, secret) // (6)
                .compact();
    }

    /**
     * 계정 생성
     * @param testDto
     * @return
     */
    public Long saveAccount(TestDto testDto) {
        if (testDto != null) {
            return testRepository.saveAccount(User.builder()
                    .id(testDto.getId())
                    .password(bCryptPasswordEncoder.encode(testDto.getPassword()))
                    .role(testDto.getRole() == null ? "ROLE_USER" : testDto.getRole())
                    .build());
        } else {
            return null;
        }
    }

    public LoginResDto login(TestDto testDto) {
        User user = testRepository.findById(testDto.getId()).orElseThrow(()->new BadCredentialsException("잘못된 계정 정보입니다."));

        if (!bCryptPasswordEncoder.matches(testDto.getPassword(),user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        String token = jwtProvider.createToken(user.getId());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        // 로그아웃을 위해 redis에 토큰 저장
        redisTemplate.opsForValue().set("JWT_TOKEN:" + user.getId(), token, jwtProvider.getExpiration(token), TimeUnit.MILLISECONDS);

        return LoginResDto.builder()
                .num(user.getNum())
                .id(user.getId())
                .nickname("없음")
                .role(user.getRole())
                .token(token)
                .build();
    }

    public User getUser(User user) {
        return user;
    }

    public void logout(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 로그아웃이 안되어 있음
        if (redisTemplate.opsForValue().get("JWT_TOKEN:" + user.getId()) != null) {
            redisTemplate.delete("JWT_TOKEN:" + user.getId()); //Token 삭제
            log.info("logout 성공!");
        } else {
            log.error("logout 실패!");
        }
    }
}
