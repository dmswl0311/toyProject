package com.cej.toy.test.controller;

import com.cej.toy.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/testApi")
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

}
