package org.proje.service;

import org.proje.dao.CustomerRepository;
import org.proje.exception.CompanyNotFoundException;
import org.proje.exception.CustomerNotFoundException;
import org.proje.model.Company;
import org.proje.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Customer> findCustomers(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Secured(value = {"ROLE_USER","ROLE_EDITOR"})
    public Customer findCustomer(Long id) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(id);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found with id:"+id);
        }
        return customer;
    }

    @Override
    @CacheEvict(cacheNames ="allCustomers",allEntries = true)
    public void createCustomer(@Valid Customer customer) {
        customerRepository.create(customer);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("k@s");
        msg.setTo("m@y");
        msg.setSubject("Customer created!");
        msg.setText("Customer entity with id :" + customer.getId() + " created successfully.");

        javaMailSender.send(msg);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }
}
