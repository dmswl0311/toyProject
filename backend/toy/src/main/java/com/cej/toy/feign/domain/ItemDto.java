package com.cej.toy.feign.domain;

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

    // 시험 정보
    private int implYy;
    private int implSeq;
    private String qualgbCd;
//    private String qualgbnm;
    private String description;
    private String docRegStartDt;
    private String docRegEndDt;
    private String docExamStartDt;
    private String docExamEndDt;
    private String docPassDt;
    private String pracRegStartDt;
    private String pracRegEndDt;
    private String pracExamStartDt;
    private String pracExamEndDt;
    private String pracPassDt;
}
