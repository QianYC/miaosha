package nju.ycqian.orderservice.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    private final AmqpAdmin admin;
    private final AmqpTemplate template;

    @Autowired
    public OrderController(AmqpAdmin admin, AmqpTemplate template) {
        this.admin = admin;
        this.template = template;
        admin.declareQueue();
    }

    @GetMapping("/order")
    public String order() {
        return "order service";
    }
}
