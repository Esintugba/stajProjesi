package org.proje.dao.jpa;

import org.proje.dao.CompanyRepository;
import org.proje.model.Company;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("companyRepository")
public class CompanyRepositoryJpaImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Company> findAll() {
        return entityManager.createQuery("from Company",Company.class).getResultList();
    }

    @Override
    public Company findById(Long id) {
        return entityManager.find(Company.class,id);
    }

    @Override
    public List<Company> findByCompanyName(String companyName) {
        return entityManager.createQuery("from Company where companyName=:companyName", Company.class)
                .setParameter("companyName",companyName).getResultList();
    }

    @Override
    public void create(Company company) {
        entityManager.persist(company);
    }

    @Override
    public Company update(Company company) {
        return entityManager.merge(company);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Company.class,id));
    }
}
