package com.ilyaden.linkcutservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LinkCutServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkCutServiceApplication.class, args);
    }

}
/*
CREATE TABLE Links(
    link varchar(100) NOT NULL,
    short varchar(100) NOT NULL
);
INSERT INTO Links(link,short) values ('www.google.com',')');
 */