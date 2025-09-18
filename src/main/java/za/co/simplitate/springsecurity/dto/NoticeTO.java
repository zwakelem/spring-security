package za.co.simplitate.springsecurity.dto;

import java.util.Date;

public record NoticeTO(
        long noticeId,
        String noticeSummary,
        String noticeDetails,
        Date noticBegDt,
        Date noticEndDt,
        Date createDt,
        Date updateDt
) {}
