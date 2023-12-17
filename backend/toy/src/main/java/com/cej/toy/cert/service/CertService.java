package com.cej.toy.cert.service;

import com.cej.toy.cert.domain.CertDto;
import com.cej.toy.cert.repository.CertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertService {

    private final CertRepository certRepository;

    int startPage = 0;
    int endPage = 0;
    int pageSize = 10;

    public List<CertDto> getAllCertList(Optional<Integer> pageNo){
        if (pageNo.isEmpty()) {
            return certRepository.getAllCertList();
        } else {
            int page = pageNo.get();
            this.pagination(page);
            return certRepository.getAllCertListPageNo(startPage, endPage);
        }
    }

    public List<CertDto> getCertList(String category) {
        if (category.isBlank()) {
            return null;
        }
        return certRepository.getCertList(category);
    }

    /**
     * page = 1
     * 1 ~ 10
     * page = 2
     * 11 ~ 20
     * page = 3
     * 21 ~ 30
     * ...
     */
    public void pagination(int page) {
        int totalPage = certRepository.getTotalPageNo();

        startPage = (page-1)*(pageSize)+page;
        endPage = pageSize;
    }
}
