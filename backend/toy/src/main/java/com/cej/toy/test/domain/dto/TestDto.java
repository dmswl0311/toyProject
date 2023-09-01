package com.cej.toy.test.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestDto {
    private int num;
    private String password;
    private String id;
    private String role;
}
