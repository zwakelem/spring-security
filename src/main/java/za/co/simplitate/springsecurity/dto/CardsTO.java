package za.co.simplitate.springsecurity.dto;

import java.util.Date;

public record CardsTO(
        long cardId,
        long customerId,
        String cardNumber,
        String cardType,
        int totalLimit,
        int amountUsed,
        int availableAmount,
        Date createDt
) {}
