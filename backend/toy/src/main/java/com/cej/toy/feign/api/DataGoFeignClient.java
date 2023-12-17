package com.cej.toy.feign.api;

import com.cej.toy.feign.domain.QualExamSchdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "data-go", url = "${spring.cloud.openfeign.data-go.url}")
public interface DataGoFeignClient {

    @GetMapping(value =  "/B490007/qualExamSchd/getQualExamSchdList?dataFormat=json", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    ResponseEntity<QualExamSchdResponse> getExamSchd(@RequestParam String ServiceKey, @RequestParam String jmCd, @RequestParam int numOfRows, @RequestParam int pageNo, @RequestParam int implYy);

}

