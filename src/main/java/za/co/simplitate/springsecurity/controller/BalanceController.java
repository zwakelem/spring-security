package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.AccountTransactionsTO;
import za.co.simplitate.springsecurity.entities.AccountTransactions;
import za.co.simplitate.springsecurity.repositories.AccountTransactionsRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactionsTO> getBalanceDetails(@RequestParam long id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);
        if (accountTransactions != null) {
            return accountTransactions.stream().map(GenericMapper::toAccountTransactionsTO).toList();
        } else {
            return null;
        }
    }
}
