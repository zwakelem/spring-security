package za.co.simplitate.springsecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.simplitate.springsecurity.entities.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
	
}
