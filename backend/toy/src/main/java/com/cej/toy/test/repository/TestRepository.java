package com.cej.toy.test.repository;

import com.cej.toy.test.domain.dto.TestDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository {
    List<TestDto> selectTestDto();
}
