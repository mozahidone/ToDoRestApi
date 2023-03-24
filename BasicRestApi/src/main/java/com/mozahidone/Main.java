package com.mozahidone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mozahidone.restapi"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}