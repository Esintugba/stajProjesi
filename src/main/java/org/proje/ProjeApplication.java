package org.proje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "projeAuditorAware")
@SpringBootApplication
@EnableConfigurationProperties(value=ProjeProperties.class)
@ServletComponentScan
public class ProjeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjeApplication.class,args);
    }
}