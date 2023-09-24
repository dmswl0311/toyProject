package com.cej.toy.feign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class QualExamSchdResponse {

    private Header header;
    private Body body;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Header{
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Body{
        private List<ItemDto> items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }
}
