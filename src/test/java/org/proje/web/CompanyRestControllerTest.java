package org.proje.web;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proje.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class CompanyRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp(){
        restTemplate=restTemplate.withBasicAuth("user2","secret");
    }

    @Test
    public void testCreateCompany(){
        Company company=new Company();
        company.setCompanyCode(6789L);
        company.setCompanyName("LC Wakiki");
        company.setAddress("Ankara");
        company.seteMail("lcwakiki@gmail.com");
        RestTemplate restTemplate=new RestTemplate();
        URI location =restTemplate.postForLocation("http://localhost:8080/rest/company",company);
        Company company2=restTemplate.getForObject(location,Company.class);
        MatcherAssert.assertThat(company2.getCompanyCode(),Matchers.equalTo(company.getCompanyCode()));
        MatcherAssert.assertThat(company2.getCompanyName(),Matchers.equalTo(company.getCompanyName()));
        MatcherAssert.assertThat(company2.getAddress(),Matchers.equalTo(company.getAddress()));
        MatcherAssert.assertThat(company2.geteMail(),Matchers.equalTo(company.geteMail()));
    }

    @Test
    public void testUpdateCompany(){
        RestTemplate restTemplate=new RestTemplate();
        Company company=restTemplate.getForObject("http://localhost:8080/rest/company/1", Company.class);
        MatcherAssert.assertThat(company.getCompanyCode(),Matchers.equalTo(1234L));
        company.setCompanyCode(1233L);
        restTemplate.put("http://localhost:8080/rest/company/1",company);
        company=restTemplate.getForObject("http://localhost:8080/rest/company/1", Company.class);
        MatcherAssert.assertThat(company.getCompanyCode(),Matchers.equalTo(1233L));
    }

    @Test
    public void testDeleteCompany(){
        restTemplate.delete("http://localhost:8080/rest/company/3");;
        try {
            restTemplate.getForEntity("http://localhost:8080/rest/company/3", Company.class);
            Assert.fail("Should have not returned company");
        }catch (HttpClientErrorException ex){
            MatcherAssert.assertThat(ex.getStatusCode().value(),Matchers.equalTo(404));
        }
    }

    @Test
    public void testGetCompanyById(){
        ResponseEntity<Company>response=restTemplate.getForEntity("http://localhost:8080/rest/company/1",Company.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getCompanyCode(),Matchers.equalTo(1234L));

    }

    @Test
    public void testGetCompaniesByCompanyName(){
        ResponseEntity<List> response=restTemplate.getForEntity("http://localhost:8080/rest/company?ln=Arzum",List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
        List<Map<String,String>> body=response.getBody();
        List<String> companyNames=body.stream().map(e->e.get("companyName")).collect(Collectors.toList());
        MatcherAssert.assertThat(companyNames,Matchers.containsInAnyOrder("Arzum"));
    }

    @Test
    public void testGetCompanies(){
        ResponseEntity<List> response=restTemplate.getForEntity("http://localhost:8080/rest/companies", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
        List<Map<String,String>> body=response.getBody();
        List<String> companyNames=body.stream().map(e->e.get("companyName")).collect(Collectors.toList());
        MatcherAssert.assertThat(companyNames, Matchers.containsInAnyOrder("Arzum","Samsung","Xiaomi"));
    }


}
