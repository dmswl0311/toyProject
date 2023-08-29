package com.cej.toy.test.repository;

import com.cej.toy.test.domain.dto.TestDto;
import com.cej.toy.test.domain.dto.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository {
    List<TestDto> selectTestDto();
    Optional<User> findById(String id);
    Long saveAccount(User user);
}
