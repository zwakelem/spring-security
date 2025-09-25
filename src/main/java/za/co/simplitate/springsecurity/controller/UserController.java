package za.co.simplitate.springsecurity.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.CustomerTO;
import za.co.simplitate.springsecurity.dto.LoginRequestTO;
import za.co.simplitate.springsecurity.dto.LoginResponseTO;
import za.co.simplitate.springsecurity.entities.Customer;
import za.co.simplitate.springsecurity.repositories.CustomerRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static za.co.simplitate.springsecurity.util.Constants.*;
import static za.co.simplitate.springsecurity.util.Constants.AUTHORITIES;
import static za.co.simplitate.springsecurity.util.Constants.JWT_TOKEN;
import static za.co.simplitate.springsecurity.util.Constants.USERNAME;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Environment env;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerTO customerTO) {
        try {
            String hashPwd = passwordEncoder.encode(customerTO.pwd());
            customerTO = new CustomerTO(customerTO.id(), customerTO.name(), customerTO.email(),
                    customerTO.mobileNumber(), hashPwd, customerTO.role(), new Date(System.currentTimeMillis()));
            Customer customer = GenericMapper.toCustomer(customerTO);
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).
                        body("Given user details are successfully registered");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body("User registration failed");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred: " + ex.getMessage());
        }
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

    /*
    this starts the authentication process manually, i.e without the help of spring framework
     */
    @PostMapping("/apiLogin")
    public ResponseEntity<LoginResponseTO> apiLogin(@RequestBody LoginRequestTO loginRequest) {
        String jwt = "";
        Authentication auth = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
                                                                                    loginRequest.password());
        Authentication authResponse = authenticationManager.authenticate(auth);
        if(authResponse != null && authResponse.isAuthenticated()) {
            String secret = !StringUtils.isEmpty(env.getProperty(JWT_SECRET_KEY)) ?
                    env.getProperty(JWT_SECRET_KEY)  :
                    JWT_SECRET_DEFAULT;
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
            jwt = getJwtToken(authResponse, secretKey);
        }
        // sending jwt token in header and body
        return ResponseEntity.status(HttpStatus.OK).header(AUTHORIZATION, jwt)
                .body(new LoginResponseTO(HttpStatus.OK.getReasonPhrase(), jwt));
    }

    private static String getJwtToken(Authentication auth, SecretKey secretKey) {
        return Jwts.builder().issuer(EAZY_BANK).subject(JWT_TOKEN)
                .claim(USERNAME, auth.getName())
                .claim(AUTHORITIES, auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(new java.util.Date(System.currentTimeMillis()))
                .expiration(new java.util.Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(secretKey).compact();
    }

}
