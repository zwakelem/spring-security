package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.LoansTO;
import za.co.simplitate.springsecurity.entities.Customer;
import za.co.simplitate.springsecurity.entities.Loans;
import za.co.simplitate.springsecurity.repositories.CustomerRepository;
import za.co.simplitate.springsecurity.repositories.LoanRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    public List<LoansTO> getLoanDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if(customerOptional.isPresent()) {
            List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customerOptional.get().getId());
            return loans.stream().map(GenericMapper::toLoansTO).toList();
        } else {
            return null;
        }
    }

}
