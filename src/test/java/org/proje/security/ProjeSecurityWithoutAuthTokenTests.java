package org.proje.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.proje.model.Company;
import org.proje.service.CompanyService;
import org.proje.service.CustomerService;
import org.proje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class ProjeSecurityWithoutAuthTokenTests {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;


    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testFindCompanies(){
        companyService.findCompanies();
    }
    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testFindCustomers(){
        customerService.findCustomers();
    }
    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testFindProducts(){
        productService.findProducts();
    }
}
