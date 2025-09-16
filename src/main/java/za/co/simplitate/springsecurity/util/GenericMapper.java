package za.co.simplitate.springsecurity.util;

import za.co.simplitate.springsecurity.dto.CustomerTO;
import za.co.simplitate.springsecurity.entities.Customer;

public class GenericMapper {

    private GenericMapper() {
    }

    public static CustomerTO toCustomerTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerTO(customer.getId(), customer.getEmail(), customer.getPwd(), customer.getRole());
    }

    public static Customer toCustomer(CustomerTO to) {
        if(to == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(to.id());
        customer.setEmail(to.email());
        customer.setPwd(to.pwd());
        customer.setRole(to.role());
        return customer;
    }
}
