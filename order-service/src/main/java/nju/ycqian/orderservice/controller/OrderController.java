package nju.ycqian.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import nju.ycqian.orderservice.service.StockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private StockClient client;

    @GetMapping("/order")
    public String order() {
        client.greet();
        return "order service";
    }
}
