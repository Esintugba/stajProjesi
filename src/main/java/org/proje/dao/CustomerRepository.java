package org.proje.dao;

import org.proje.model.Customer;

import java.security.acl.Owner;
import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();
    Customer findById(Long id);
    List<Customer> findByLastName(String lastName);
    void create(Customer customer);
    Customer update(Customer customer);
    void delete(Long id);
}
