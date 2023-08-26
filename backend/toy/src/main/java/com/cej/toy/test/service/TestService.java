package com.cej.toy.test.service;

import com.cej.toy.test.domain.dto.TestDto;
import com.cej.toy.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public List<String> getTestPwd(){
        List<TestDto> testDtoList = testRepository.selectTestDto();
        if (testDtoList != null && !testDtoList.isEmpty()) {
            return testDtoList
                    .stream()
                    .map(TestDto::getPwd)
                    .toList();
        }
        return null;
    }

}
