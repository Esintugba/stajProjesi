package org.proje.security;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proje.model.Company;
import org.proje.model.Customer;
import org.proje.model.Product;
import org.proje.service.CompanyService;
import org.proje.service.CustomerService;
import org.proje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.profiles.active=dev")
public class ProjeSecurityWithValidAuthTokenTests {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @Before
    public void setUp(){
        TestingAuthenticationToken auth = new TestingAuthenticationToken("xxx", "secret","ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }


    @Test
    public void testFindCompanies() {
        List<Company> companies = companyService.findCompanies();
        MatcherAssert.assertThat(companies.size(), Matchers.equalTo(15));
    }


    @Test
    public void testFindCustomers() {
        List<Customer> customers = customerService.findCustomers();
        MatcherAssert.assertThat(customers.size(), Matchers.equalTo(15));
    }


    @Test
    public void testFindProducts() {
        List<Product> products = productService.findProducts();
        MatcherAssert.assertThat(products.size(), Matchers.equalTo(15));
    }
}
