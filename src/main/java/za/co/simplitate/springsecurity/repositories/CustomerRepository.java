package za.co.simplitate.springsecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import za.co.simplitate.springsecurity.entities.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
