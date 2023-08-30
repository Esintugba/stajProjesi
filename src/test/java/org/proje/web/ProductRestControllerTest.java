package org.proje.web;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.proje.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductRestControllerTest {
    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp(){
        restTemplate=new RestTemplate();
    }

    @Test
    public void testCreateProduct(){
        Product product=new Product();
        product.setProductCode(999L);
        product.setName("JBL Tune Flex NC TWS Kulak İçi Bluetooth Kulaklık");
        product.setCategories("Kulaklıklar");
        product.setExplanation("Müziğin keyfini çıkarabileceğiniz uzun pil ömürlü kablosuz kulaklıklar");
        product.setPrice(2889L);
        RestTemplate restTemplate=new RestTemplate();
        URI location =restTemplate.postForLocation("http://localhost:8080/rest/product",product);
        Product product2=restTemplate.getForObject(location,Product.class);
        MatcherAssert.assertThat(product2.getProductCode(), Matchers.equalTo(product.getProductCode()));
        MatcherAssert.assertThat(product2.getName(),Matchers.equalTo(product.getName()));
        MatcherAssert.assertThat(product2.getCategories(),Matchers.equalTo(product.getCategories()));
        MatcherAssert.assertThat(product2.getExplanation(),Matchers.equalTo(product2.getExplanation()));
        MatcherAssert.assertThat(product2.getPrice(),Matchers.equalTo(product2.getExplanation()));

    }

    @Test
    public void testUpdateProduct(){
        RestTemplate restTemplate=new RestTemplate();
        Product product=restTemplate.getForObject("http://localhost:8080/rest/product/4", Product.class);
        MatcherAssert.assertThat(product.getProductCode(),Matchers.equalTo(666L));
        product.setProductCode(110L);
        restTemplate.put("http://localhost:8080/rest/product/4",product);
        product=restTemplate.getForObject("http://localhost:8080/rest/product/4", Product.class);
        MatcherAssert.assertThat(product.getProductCode(),Matchers.equalTo(110L));
    }

    @Test
    public void testDeleteProduct(){
        restTemplate.delete("http://localhost:8080/rest/product/3");;
        try {
            restTemplate.getForEntity("http://localhost:8080/rest/product/3", Product.class);
            Assert.fail("Should have not returned company");
        }catch (HttpClientErrorException ex){
            MatcherAssert.assertThat(ex.getStatusCode().value(),Matchers.equalTo(404));
        }
    }

    @Test
    public void testGetProductById(){
        ResponseEntity<Product> response=restTemplate.getForEntity("http://localhost:8080/rest/product/1",Product.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getProductCode(),Matchers.equalTo(333L));

    }


}
