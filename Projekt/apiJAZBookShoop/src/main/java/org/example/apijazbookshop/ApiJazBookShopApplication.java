package org.example.apijazbookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class ApiJazBookShopApplication {

//	@Autowired
//	private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(ApiJazBookShopApplication.class, args);
    }


//	@Override
//	public void run(String... args) throws Exception {
//		bookService.generateBookOrders();
}

