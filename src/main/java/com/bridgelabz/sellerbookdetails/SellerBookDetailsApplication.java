package com.bridgelabz.sellerbookdetails;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class SellerBookDetailsApplication {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
    	final Logger log = LoggerFactory.getLogger(SellerBookDetailsApplication.class);
        SpringApplication.run(SellerBookDetailsApplication.class, args);
        System.out.println("--------------------------------");
        log.info("\nHello! Welcome to Book Store Seller Project!");
    }
}
