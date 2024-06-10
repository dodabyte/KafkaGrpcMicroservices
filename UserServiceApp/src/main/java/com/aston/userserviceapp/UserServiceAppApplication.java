package com.aston.userserviceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class UserServiceAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceAppApplication.class, args);
    }
}
