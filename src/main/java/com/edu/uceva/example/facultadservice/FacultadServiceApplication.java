package com.edu.uceva.example.facultadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FacultadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacultadServiceApplication.class, args);
    }

}
