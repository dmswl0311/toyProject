package com.cej.toy.test.controller;

import com.cej.toy.config.JwtProvider;
import com.cej.toy.test.domain.dto.LoginResDto;
import com.cej.toy.test.domain.dto.TestDto;
import com.cej.toy.test.domain.dto.User;
import com.cej.toy.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/testApi")
@Slf4j
public class TestController {
    private final TestService testService;
    private final JwtProvider jwtProvider;

    @GetMapping("/testPwd")
    public List<String> getTestPwd(){
        return testService.getTestPwd();
    }

    @GetMapping("/token")
    public String makeJwtToken() {
        return testService.getToken();
    }

    /**
     * 계성 생성 (회원가입)
     * @param testDto
     */
    @PostMapping("/join")
    public String join(@RequestBody TestDto testDto){
        Long result = testService.saveAccount(testDto);
        if (result != null && result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 로그아웃
     * @param
     */
    @PostMapping("/logout")
    public void logout(){
        log.info("logout");
        testService.logout();
    }

    @GetMapping("/info")
    public String getInfo(@AuthenticationPrincipal User user) {
        log.info("/info");
        return user.getId();
    }

    @GetMapping("/verify-token")
    public Boolean verifyToken(String token) {
       String result = jwtProvider.getUserId(token.split(" ")[1]);
        return jwtProvider.verifyToken(token);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResDto> login(@RequestBody TestDto testDto) throws Exception{
        return new ResponseEntity<>(testService.login(testDto), HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal User user) throws Exception {
        return new ResponseEntity<>(testService.getUser(user), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<Boolean> admin() {
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
