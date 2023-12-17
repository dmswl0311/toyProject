package com.cej.toy.cert.repository;

import com.cej.toy.cert.domain.CertDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertRepository {
    List<CertDto> getAllCertList();
    List<CertDto> getAllCertListPageNo(int startPage, int endPage);
    int getTotalPageNo();
    List<CertDto> getCertList(String category);
}
