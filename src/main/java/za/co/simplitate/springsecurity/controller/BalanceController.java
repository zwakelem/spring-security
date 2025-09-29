package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.AccountTransactionsTO;
import za.co.simplitate.springsecurity.entities.AccountTransactions;
import za.co.simplitate.springsecurity.entities.Customer;
import za.co.simplitate.springsecurity.repositories.AccountTransactionsRepository;
import za.co.simplitate.springsecurity.repositories.CustomerRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactionsTO> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if(customerOptional.isPresent()) {
            List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                    findByCustomerIdOrderByTransactionDtDesc(customerOptional.get().getId());
            return accountTransactions.stream().map(GenericMapper::toAccountTransactionsTO).toList();
        } else {
            return null;
        }
    }
}
