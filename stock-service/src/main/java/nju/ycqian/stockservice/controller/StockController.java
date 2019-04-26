package nju.ycqian.stockservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @GetMapping("/stock")
    public String helloWorld() {
        return "stock service";
    }
}
