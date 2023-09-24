package com.cej.toy.feign.service;

import com.cej.toy.feign.repository.OpenApiRepository;
import com.cej.toy.feign.api.OpenApiFeignClient;
import com.cej.toy.feign.domain.InquiryInformationResponse;
import com.cej.toy.feign.domain.InquiryListNationalQualifcationResponse;
import com.cej.toy.feign.domain.ItemDto;
import feign.codec.DecodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenApiService {
    private final OpenApiFeignClient openApiFeignClient;

    private final OpenApiRepository openApiRepository;

    @Value("${open-api-key}") String ServiceKey;

    public Object getCertInfo(String jmcd){
        Object result = null;
        try {
            ResponseEntity<InquiryInformationResponse> response = openApiFeignClient.getCertInfo(ServiceKey, jmcd);
            result = Objects.requireNonNull(response.getBody()).getResponse().getBody().getItems().getItem();
        } catch (DecodeException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Object getCertMetaInfo(String jmcd){
        Object result = null;
        try {
            ResponseEntity<InquiryListNationalQualifcationResponse> response = openApiFeignClient.getCertMetaInfo(ServiceKey);
            List<ItemDto> items = Objects.requireNonNull(response.getBody()).getResponse().getBody().getItems().getItem();
            result = items.stream().filter(item -> item.getJmcd().equals(jmcd)).toList();
        } catch (DecodeException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Boolean saveCert(){
        try {
            // api 호출
            ResponseEntity<InquiryListNationalQualifcationResponse> response = openApiFeignClient.getCertMetaInfo(ServiceKey);
            List<ItemDto> items = Objects.requireNonNull(response.getBody()).getResponse().getBody().getItems().getItem();

            // db 저장
            int result = openApiRepository.saveCertMetaInfo(items);
            if (result > 0) {
                return Boolean.TRUE;
            }
            return Boolean.TRUE;
        } catch (DecodeException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
