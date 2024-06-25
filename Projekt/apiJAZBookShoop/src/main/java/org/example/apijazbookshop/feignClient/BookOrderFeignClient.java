package org.example.apijazbookshop.feignClient;

import io.swagger.model.BookOrderRequest;
import io.swagger.model.BookOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "BookOrder", url = "http://localhost:8086")
public interface BookOrderFeignClient {

    @PostMapping("/book-order")
    ResponseEntity<BookOrderResponse> createBookOrder(@RequestBody BookOrderRequest bookOrderRequest);

    @GetMapping("/book-order")
    ResponseEntity<List<BookOrderResponse>> getAllOrders();
}
