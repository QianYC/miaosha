package nju.ycqian.stockservice.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface In {
    String INPUT = "fromOrder";

    @Input(In.INPUT)
    SubscribableChannel input();
}
