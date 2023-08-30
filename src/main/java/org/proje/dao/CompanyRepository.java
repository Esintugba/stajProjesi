package org.proje.dao;

import org.proje.model.Company;

import java.security.acl.Owner;
import java.util.List;

public interface CompanyRepository {

    List<Company> findAll();
    Company findById(Long id);
    List<Company> findByCompanyName(String companyName);
    void create (Company company);
    Company update(Company company);
    void delete(Long id);
}
