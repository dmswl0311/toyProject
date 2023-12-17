package com.cej.toy.feign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 국가자격 종목별 자격정보
 */
@Getter
@Setter
@NoArgsConstructor
public class InquiryInformationResponse {
    private Response response;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response{
        private Header header;
        private Body body;
    }
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
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Items {
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        private List<ItemDto> item;
    }
}
