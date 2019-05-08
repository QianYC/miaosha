package nju.ycqian.stockservice.controller;

import com.alibaba.fastjson.JSONObject;
import nju.ycqian.stockservice.entity.Goods;
import nju.ycqian.stockservice.service.In;
import nju.ycqian.stockservice.service.StockService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableBinding(In.class)
public class StockController {

    @Autowired
    StockService stockService;

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

    @GetMapping("/goods")
    public List<Goods> getGoods() {
        return stockService.listGoods();
    }

    @GetMapping("/good/{id}")
    public Goods getGood(@PathVariable("id") long id) {
        return stockService.goodsDetail(id);
    }

    @PostMapping("/kill/{id}")
    public JSONObject kill(@PathVariable("id") long id) {
        return stockService.reduceGoodsAmount(id, 1);
    }

    @PostMapping("/cancel/{id}")
    public JSONObject cancel(@PathVariable("id") long id) {
        return stockService.increaseGoodsAmount(id, 1);
    }
}
