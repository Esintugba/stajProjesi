package org.proje.service;

import org.proje.exception.CustomerNotFoundException;
import org.proje.model.Customer;

import javax.validation.Valid;
import java.util.List;

public interface CustomerService {


    List<Customer> findCustomers();
    List<Customer> findCustomers(String lastName);
    Customer findCustomer(Long id) throws CustomerNotFoundException;
    void createCustomer(@Valid Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Long id);
}
