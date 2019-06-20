package nju.ycqian.orderservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import nju.ycqian.orderservice.service.OrderReqIn;
import nju.ycqian.orderservice.service.OrderService;
import nju.ycqian.orderservice.service.Out;
import nju.ycqian.orderservice.service.StockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@Slf4j
@EnableBinding(OrderReqIn.class)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public JSONObject getOrders(@RequestParam("userId") int userId) {
        return orderService.getAllOrders(userId);
    }

    @GetMapping("/getNew")
    public long getNewOrderId(@RequestParam("userId") int userId, @RequestParam("goodId") int goodId) {
        return orderService.getRecentOrderId(userId, goodId);
    }

    @GetMapping("/orders/{id}")
    public JSONObject getOrder(@PathVariable("id") long orderId) {
        return orderService.getOrderById(orderId);
    }

    @StreamListener(OrderReqIn.INPUT)
    public void receive(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        int userId = jsonObject.getInteger("userId");
        int goodId = jsonObject.getInteger("goodId");
        double price = jsonObject.getDouble("price");
        orderService.saveOrder(userId, goodId, price);
    }
}
