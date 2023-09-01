package com.cej.toy.test.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResDto {
    private int num;
    private String id;
    private String role;
    private String nickname;
    private String token;
}
