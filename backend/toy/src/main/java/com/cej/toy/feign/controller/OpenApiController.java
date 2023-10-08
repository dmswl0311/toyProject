package com.cej.toy.feign.controller;

import com.cej.toy.feign.service.DataGoService;
import com.cej.toy.feign.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/open-api")
public class OpenApiController {
    private final OpenApiService openApiService;
    private final DataGoService dataGoService;

    /**
     * 국가자격 종목별 자격정보
     * InquiryInformationTradeNTQSVC
     * @param jmcd
     * @return
     */
    @GetMapping("/cert-info")
    public ResponseEntity<?> getCertInfo(@RequestParam("jmcd") String jmcd){
        return new ResponseEntity<>(openApiService.getCertInfo(jmcd), HttpStatus.OK);
    }

    /**
     * 국가자격 종목 목록 정보 중 특정 기본 자격정보
     * InquiryListNationalQualifcationSVC
     * @param jmcd
     * @return
     */
    @GetMapping("/cert-meta-info")
    public ResponseEntity<?> getCertMetaInfo(@RequestParam("jmcd") String jmcd){
        return new ResponseEntity<>(openApiService.getCertMetaInfo(jmcd), HttpStatus.OK);
    }

    /**
     * 국가자격 종목 목록 정보 db 저장
     */
    @PostMapping("/cert-meta-info")
    public ResponseEntity<Boolean> saveCertMetaInfo(){
        return new ResponseEntity<>(openApiService.saveCert(), HttpStatus.OK);
    }

    /**
     * 한국산업인력공단_국가자격 시험일정 조회 서비스
     * @param jmCd
     * @param implYy
     * @return
     */
    @GetMapping("/exam-schd")
    public ResponseEntity<?> getExamSchd(String jmCd, int implYy){
        return new ResponseEntity<>(dataGoService.getExamSchd(jmCd, implYy), HttpStatus.OK);
    }
}
