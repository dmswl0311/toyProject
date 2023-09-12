package com.cej.toy.openApi.api;

import com.cej.toy.openApi.domain.InquiryInformationResponse;
import com.cej.toy.openApi.domain.InquiryListNationalQualifcationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "open-api", url = "${spring.cloud.openfeign.open-api.url}")
public interface OpenApiFeignClient {
    
    @GetMapping(value =  "/api/service/rest/InquiryInformationTradeNTQSVC/getList?_type=json", consumes = "application/x-www-form-urlencoded")
    ResponseEntity<InquiryInformationResponse> getCertInfo(@RequestParam String ServiceKey, @RequestParam String jmCd);

    @GetMapping(value =  "/api/service/rest/InquiryListNationalQualifcationSVC/getList?_type=json", consumes = "application/x-www-form-urlencoded")
    ResponseEntity<InquiryListNationalQualifcationResponse> getCertMetaInfo(@RequestParam String ServiceKey);

}

