package za.co.simplitate.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import za.co.simplitate.springsecurity.exceptions.CustomAccessDeniedHandler;
import za.co.simplitate.springsecurity.exceptions.CustomBasicAuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((reqs) -> reqs.anyRequest().permitAll());
//        http.authorizeHttpRequests((reqs) -> reqs.anyRequest().denyAll());
        http.sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession")
                .maximumSessions(3)
                .maxSessionsPreventsLogin(true))
            .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((reqs) -> reqs
            .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
            .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession").permitAll())
            .formLogin(withDefaults())
            .httpBasic(hbc -> hbc.authenticationEntryPoint(
                new CustomBasicAuthenticationEntryPoint()))
            .exceptionHandling(ehc -> ehc.accessDeniedHandler(
                new CustomAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
