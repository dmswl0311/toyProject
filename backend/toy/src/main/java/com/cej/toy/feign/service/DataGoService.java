package com.cej.toy.feign.service;

import com.cej.toy.feign.api.DataGoFeignClient;
import com.cej.toy.feign.api.OpenApiFeignClient;
import com.cej.toy.feign.domain.InquiryInformationResponse;
import com.cej.toy.feign.domain.InquiryListNationalQualifcationResponse;
import com.cej.toy.feign.domain.ItemDto;
import com.cej.toy.feign.domain.QualExamSchdResponse;
import com.cej.toy.feign.repository.OpenApiRepository;
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
public class DataGoService {
    private final DataGoFeignClient dataGoFeignClient;

    @Value("${open-api-key}") String ServiceKey;

    public Object getExamSchd(String jmCd, int implYy){
        Object result = null;
        try {
            ResponseEntity<QualExamSchdResponse> response = dataGoFeignClient.getExamSchd(ServiceKey, jmCd, 50, 1, implYy);
            List<ItemDto> items = Objects.requireNonNull(response.getBody()).getBody().getItems();
            result = items;
        } catch (DecodeException e) {
            e.printStackTrace();
        }

        return result;
    }

}
