package com.cej.toy.feign.repository;

import com.cej.toy.feign.domain.ItemDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenApiRepository {
    int saveCertMetaInfo(List<ItemDto> items);
}
