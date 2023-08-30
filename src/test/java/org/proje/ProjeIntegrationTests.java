package org.proje;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proje.model.Company;
import org.proje.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class ProjeIntegrationTests {

    @Autowired
    private CompanyService companyService;

    @Test
    public void testFindCompanies(){
        List<Company> companies=companyService.findCompanies();
        MatcherAssert.assertThat(companies.size(), Matchers.equalTo(15));
    }
}
