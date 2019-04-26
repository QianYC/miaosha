package nju.ycqian.stockservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @GetMapping("")
    public String helloWorld() {
        return "stock service";
    }

    @GetMapping("/hello")
    public void greet() {
        System.out.println("greeting from order service");
    }
}
