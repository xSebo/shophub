package com.example.clientproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ClientProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientProjectApplication.class, args);
    }

}
