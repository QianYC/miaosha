package nju.ycqian.stockservice.controller;

import nju.ycqian.stockservice.service.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(In.class)
public class StockController {

    @GetMapping("")
    public String helloWorld() {
        return "stock service";
    }

    @GetMapping("/hello")
    public void greet() {
        System.out.println("greeting from order service");
    }

    @StreamListener(In.INPUT)
    public void receive(String msg) {
        System.out.println("receive message from order service : " + msg);
    }
}
