package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.CardsTO;
import za.co.simplitate.springsecurity.entities.Cards;
import za.co.simplitate.springsecurity.entities.Customer;
import za.co.simplitate.springsecurity.repositories.CardsRepository;
import za.co.simplitate.springsecurity.repositories.CustomerRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<CardsTO> getCardDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if(customerOptional.isPresent()) {
            List<Cards> cards = cardsRepository.findByCustomerId(customerOptional.get().getId());
            return cards != null ? cards.stream().map(GenericMapper::toCardsTO).toList() : null;
        } else {
            return null;
        }
    }

}
