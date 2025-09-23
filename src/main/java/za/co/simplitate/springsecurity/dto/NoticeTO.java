package za.co.simplitate.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public record NoticeTO(
        long noticeId,
        String noticeSummary,
        String noticeDetails,
        Date noticBegDt,
        Date noticEndDt,

        @JsonIgnore
        Date createDt,

        @JsonIgnore
        Date updateDt
) {}
