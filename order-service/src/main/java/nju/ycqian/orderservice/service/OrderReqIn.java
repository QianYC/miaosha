package nju.ycqian.orderservice.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderReqIn {

    String INPUT = "orderRequest";

    @Input(OrderReqIn.INPUT)
    SubscribableChannel orderInput();

}
