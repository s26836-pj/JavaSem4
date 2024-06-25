package org.example.apijazbookorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiJazBookOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiJazBookOrderApplication.class, args);
    }

}
