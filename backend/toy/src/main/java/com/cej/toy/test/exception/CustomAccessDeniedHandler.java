package com.cej.toy.test.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 권한 문제가 발생했을 때 이 부분을 호출한다.
        JSONObject responseJson = new JSONObject();
        try {
            responseJson.put("message", accessDeniedException.getMessage());
            response.setStatus(403);
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(responseJson);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        log.error("권한이 없는 사용자!!! message : "+ accessDeniedException.getMessage());
    }
}
