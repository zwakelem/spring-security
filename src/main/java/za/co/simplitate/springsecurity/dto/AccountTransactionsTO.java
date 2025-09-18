package za.co.simplitate.springsecurity.dto;

import java.util.Date;

public record AccountTransactionsTO(
        String transactionId,
        long accountNumber,
        long customerId,
        Date transactionDt,
        String transactionSummary,
        String transactionType,
        int transactionAmt,
        int closingBalance,
        Date createDt
) {}
