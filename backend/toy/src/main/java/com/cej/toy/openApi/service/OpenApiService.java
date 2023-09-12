package com.cej.toy.openApi.service;

import com.cej.toy.openApi.api.OpenApiFeignClient;
import com.cej.toy.openApi.domain.InquiryInformationResponse;
import com.cej.toy.openApi.domain.InquiryListNationalQualifcationResponse;
import com.cej.toy.openApi.domain.ItemDto;
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
}
