package org.proje.dao.mem;

import org.proje.dao.CompanyRepository;
import org.proje.model.Company;
import org.springframework.stereotype.Repository;

import java.security.acl.Owner;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CompanyRepositoryInMemoryImpl implements CompanyRepository {

    private Map<Long,Company> companyMap=new HashMap<>();

    public CompanyRepositoryInMemoryImpl(){
        Company company1=new Company();
        company1.setId(1L);
        company1.setCompanyCode(1234L);
        company1.setCompanyName("Arzum");
        company1.setAddress("İstanbul");
        company1.seteMail("arzum@gmail.com");

        Company company2 = new Company();
        company2.setId(2L);
        company2.setCompanyCode(2345L);
        company2.setCompanyName("Oppo");
        company2.setAddress("İstanbul");
        company2.seteMail("oppo@gmail.com");

        Company company3 = new Company();
        company3.setId(3L);
        company3.setCompanyCode(3456L);
        company3.setCompanyName("Casper");
        company3.setAddress("Ankara");
        company3.seteMail("casper@gmail.com");

        companyMap.put(company1.getId(), company1);
        companyMap.put(company2.getId(), company2);
        companyMap.put(company3.getId(), company3);
    }
    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companyMap.values());
    }

    @Override
    public Company findById(Long id) {
        return companyMap.get(id);
    }

    @Override
    public List<Company> findByCompanyName(String companyName) {
        return companyMap.values().stream().filter(o->o.getCompanyName().equals(companyName)).collect(Collectors.toList());
    }

    @Override
    public void create(Company company) {
        company.setId(new Date().getTime());
        companyMap.put(company.getId(),company);
    }

    @Override
    public Company update(Company company) {
        companyMap.replace(company.getId(),company);
        return company;
    }

    @Override
    public void delete(Long id) {
        companyMap.remove(id);
    }
}
