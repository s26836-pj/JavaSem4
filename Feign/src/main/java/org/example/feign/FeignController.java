package org.example.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeignController {
    private final FeignClient feignClient;

    @GetMapping("/flight")
    public void flight() {
        System.out.println(feignClient.flight());

    }

}

