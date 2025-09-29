package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.AccountTO;
import za.co.simplitate.springsecurity.entities.Accounts;
import za.co.simplitate.springsecurity.entities.Customer;
import za.co.simplitate.springsecurity.repositories.AccountsRepository;
import za.co.simplitate.springsecurity.repositories.CustomerRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public AccountTO getAccountDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if (customerOptional.isPresent()) {
            Accounts accounts = accountsRepository.findByCustomerId(customerOptional.get().getId());
            return accounts != null ? GenericMapper.toAccountTO(accounts) : null;
        }
        return null;
    }

}
