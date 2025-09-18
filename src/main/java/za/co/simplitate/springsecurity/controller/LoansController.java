package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.LoansTO;
import za.co.simplitate.springsecurity.entities.Loans;
import za.co.simplitate.springsecurity.repositories.LoanRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<LoansTO> getLoanDetails(@RequestParam long id) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans != null) {
            return loans.stream().map(GenericMapper::toLoansTO).toList();
        } else {
            return null;
        }
    }

}
