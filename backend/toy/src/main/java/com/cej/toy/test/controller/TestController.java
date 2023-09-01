package com.cej.toy.test.controller;

import com.cej.toy.test.domain.dto.TestDto;
import com.cej.toy.test.domain.dto.User;
import com.cej.toy.test.service.TestService;
import com.cej.toy.test.service.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/testApi")
@Slf4j
public class TestController {
    private final TestService testService;

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
    public String logout(HttpServletRequest request, HttpServletResponse response){
        log.info("logout");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "success";
    }

    @GetMapping("/info")
    public String getInfo(@AuthenticationPrincipal User user) {
        log.info("/info");
        return user.getId();
    }

}
