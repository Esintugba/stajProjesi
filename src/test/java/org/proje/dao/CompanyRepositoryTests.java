package org.proje.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.proje.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class CompanyRepositoryTests {

    @Autowired
    private CompanyRepository companyRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test(expected = PersistenceException.class)
    public void testCreateCompany(){
        Company company=new Company();
        company.setCompanyCode(3300L);
        company.setCompanyName("JBL");
        company.setAddress(null);
        company.seteMail(null);
        companyRepository.create(company);
        entityManager.flush();
    }
}
