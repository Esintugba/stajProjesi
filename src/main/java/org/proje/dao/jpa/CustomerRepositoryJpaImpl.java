package org.proje.dao.jpa;

import org.proje.dao.CustomerRepository;
import org.proje.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("customerRepository")
public class CustomerRepositoryJpaImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("from Customer ", Customer.class).getResultList();
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class,id);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return entityManager.createQuery("from Customer where lastName=:lastName", Customer.class)
                .setParameter("lastName",lastName).getResultList();
    }

    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Customer.class,id));
    }
}
