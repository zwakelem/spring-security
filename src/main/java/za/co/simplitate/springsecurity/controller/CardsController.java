package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.CardsTO;
import za.co.simplitate.springsecurity.entities.Cards;
import za.co.simplitate.springsecurity.repositories.CardsRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<CardsTO> getCardDetails(@RequestParam long id) {
        List<Cards> cards = cardsRepository.findByCustomerId(id);
        if (cards != null ) {
            return cards.stream().map(GenericMapper::toCardsTO).toList();
        } else {
            return null;
        }
    }

}
