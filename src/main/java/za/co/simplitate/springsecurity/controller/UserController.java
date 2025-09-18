package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.CustomerTO;
import za.co.simplitate.springsecurity.entities.Customer;
import za.co.simplitate.springsecurity.repositories.CustomerRepository;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(CustomerTO customerTo) {
        try {
//            String hashPassword = passwordEncoder.encode(customerTo.pwd());
            Customer customer = new Customer();
            Customer savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("User registered successfully");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User not registered!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
