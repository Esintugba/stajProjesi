package org.proje.service;

import org.proje.exception.CompanyNotFoundException;
import org.proje.model.Company;

import javax.validation.Valid;
import java.util.List;

public interface CompanyService {

    List<Company> findCompanies();
    List<Company> findCompanies(String companyName);
    Company findCompany(Long id) throws CompanyNotFoundException;
    void createCompany(@Valid Company company);
    void updateCompany(Company company);
    void deleteCompany(Long id);
}
