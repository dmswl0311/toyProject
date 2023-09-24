package com.cej.toy.feign.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 국가자격 종목 목록 정보
 */
@NoArgsConstructor
@Getter
@Setter
public class InquiryListNationalQualifcationResponse {

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
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Items {
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        private List<ItemDto> item;
    }
}
