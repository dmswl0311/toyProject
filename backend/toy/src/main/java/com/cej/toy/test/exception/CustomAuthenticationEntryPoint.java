package com.cej.toy.test.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 인증문제가 발생했을 때 이 부분을 호출한다.
        JSONObject responseJson = new JSONObject();
        try {
            responseJson.put("message", authException.getMessage());
            response.setStatus(401);
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(responseJson);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        log.error("로그인 필요!!! message : "+ authException.getMessage());
    }
}
