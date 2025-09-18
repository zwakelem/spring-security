package za.co.simplitate.springsecurity.dto;

import java.util.Date;

public record AccountTO(
        long customerId,
        long accountNumber,
        String accountType,
        String branchAddress,
        Date createDt
) {}
