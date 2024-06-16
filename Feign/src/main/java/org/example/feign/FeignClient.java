package org.example.feign;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@org.springframework.cloud.openfeign.FeignClient (name = "default",url = "localhost:8080")

public interface FeignClient {
    @GetMapping("/flights")
    String flight();

}
