package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.AccountTO;
import za.co.simplitate.springsecurity.entities.Accounts;
import za.co.simplitate.springsecurity.repositories.AccountsRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public AccountTO getAccountDetails(@RequestParam long id) {
        Accounts accounts = accountsRepository.findByCustomerId(id);

        if (accounts != null) {
            return GenericMapper.toAccountTO(accounts);
        } else {
            return null;
        }
    }

}
