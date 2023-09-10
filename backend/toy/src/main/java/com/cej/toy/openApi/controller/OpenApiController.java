package com.cej.toy.openApi.controller;

import com.cej.toy.openApi.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api")
public class OpenApiController {
    private final OpenApiService openApiService;

    @GetMapping("/test")
    public ResponseEntity<Object> getList(){
        return new ResponseEntity<>(openApiService.getList(), HttpStatus.OK);
    }
}
