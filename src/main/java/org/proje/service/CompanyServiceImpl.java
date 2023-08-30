package org.proje.service;

import org.proje.dao.CompanyRepository;
import org.proje.exception.CompanyNotFoundException;
import org.proje.model.Company;
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
public class CompanyServiceImpl implements CompanyService{

    private CompanyRepository companyRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Company> findCompanies() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Company> findCompanies(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Secured(value = {"ROLE_USER","ROLE_EDITOR"})
    public Company findCompany(Long id) throws CompanyNotFoundException {
        Company company=companyRepository.findById(id);
        if(company==null){
            throw new CompanyNotFoundException("Company not found with id:"+id);
        }
        return company;
    }

    @Override
    @CacheEvict(cacheNames ="allCompanys",allEntries = true)
    public void createCompany(@Valid Company company) {
        companyRepository.create(company);
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom("k@s");
        msg.setTo("m@y");
        msg.setSubject("Company created!");
        msg.setText("Company entity with id:"+company.getId()+"created.successfully");
        javaMailSender.send(msg);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.update(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.delete(id);
    }
}
