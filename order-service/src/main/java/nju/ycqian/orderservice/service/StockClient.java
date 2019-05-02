package nju.ycqian.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("service-stock")
public interface StockClient {

    @GetMapping("/hello")
    void greet();
}
