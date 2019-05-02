package nju.ycqian.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import nju.ycqian.orderservice.service.Out;
import nju.ycqian.orderservice.service.StockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@EnableBinding(Out.class)
public class OrderController {

    @Autowired
    private StockClient client;

    @Autowired
    private Out out;

    @GetMapping("/")
    public String order() {
        client.greet();
        return "order service";
    }

    @GetMapping("/msg")
    public String order1() {
        out.output().send(MessageBuilder.withPayload("message from order service").build());
        return "send a msg to stock service";
    }
}
