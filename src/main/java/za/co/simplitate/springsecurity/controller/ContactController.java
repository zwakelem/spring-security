package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.ContactTO;
import za.co.simplitate.springsecurity.entities.Contact;
import za.co.simplitate.springsecurity.repositories.ContactRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.sql.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    @PostMapping("/contact")
    public ContactTO saveContactInquiryDetails(@RequestBody ContactTO contactTO) {
        Contact contact = new Contact();
        contact.setContactId(getServiceReqNumber());
        contact.setContactName(contactTO.contactName());
        contact.setContactEmail(contactTO.contactEmail());
        contact.setSubject(contactTO.subject());
        contact.setMessage(contactTO.message());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return GenericMapper.toContactTO(contactRepository.save(contact));
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
