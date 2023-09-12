package com.cej.toy.openApi.controller;

import com.cej.toy.openApi.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api")
public class OpenApiController {
    private final OpenApiService openApiService;

    /**
     * 국가자격 종목별 자격정보
     * @param jmcd
     * @return
     */
    @GetMapping("/test")
    public ResponseEntity<?> getCertInfo(@RequestParam("jmcd") String jmcd){
        return new ResponseEntity<>(openApiService.getCertInfo(jmcd), HttpStatus.OK);
    }

    /**
     * 국가자격 종목 목록 정보 중 특정 기본 자격정보
     * @param jmcd
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<?> getCertMetaInfo(@RequestParam("jmcd") String jmcd){
        return new ResponseEntity<>(openApiService.getCertMetaInfo(jmcd), HttpStatus.OK);
    }
}
