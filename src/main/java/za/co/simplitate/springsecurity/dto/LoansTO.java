package za.co.simplitate.springsecurity.dto;

import java.util.Date;

public record LoansTO(
        long loanNumber,
        long customerId,
        Date startDt,
        String loanType,
        int totalLoan,
        int amountPaid,
        int outstandingAmount,
        Date createDt
) {}
