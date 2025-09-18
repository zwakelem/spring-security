package za.co.simplitate.springsecurity.dto;

import java.util.Date;

public record CustomerTO(
        long id,
        String name,
        String email,
        String mobileNumber,
        String pwd,
        String role,
        Date createDt
) {}
