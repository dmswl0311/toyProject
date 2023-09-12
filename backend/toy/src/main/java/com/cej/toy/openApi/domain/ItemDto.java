package com.cej.toy.openApi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    private String contents;
    private String infogb;
    private String jmfldnm;
    private int mdobligfldcd;
    private String mdobligfldnm;
    private int obligfldcd;
    private String obligfldnm;

    // 국가자격 종목 목록 정보
    private String jmcd;
    private String qualgbcd;
    private String qualgbnm;
    private String seriescd;
    private String seriesnm;
}
