package org.proje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ProjeConfiguration {
    @Autowired
    private ProjeProperties projeProperties;

    @PostConstruct
    public void init(){
        System.out.println(("Display customers with products:" + projeProperties.isDisplayCustomersWithProducts()));
    }

}
