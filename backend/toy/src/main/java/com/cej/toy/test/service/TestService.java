package com.cej.toy.test.service;

import com.cej.toy.test.domain.dto.TestDto;
import com.cej.toy.test.domain.dto.User;
import com.cej.toy.test.repository.TestRepository;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TestRepository testRepository;
    @Value("${jwt.secret}") String secret;

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
                    .build());
        } else {
            return null;
        }

    }
}
