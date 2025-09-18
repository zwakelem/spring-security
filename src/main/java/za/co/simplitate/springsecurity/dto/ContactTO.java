package za.co.simplitate.springsecurity.dto;

import java.util.Date;

public record ContactTO(
        String contactId,
        String contactName,
        String contactEmail,
        String subject,
        String message,
        Date createDt
) {}
