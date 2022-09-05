package com.ilyaden.linkcutservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class LinkCutServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(LinkCutServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LinkCutServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
/*
CREATE TABLE Links(
    link varchar(100) NOT NULL,
    short varchar(100) NOT NULL
);
INSERT INTO Links(link,short) values ('www.google.com',')');
 */