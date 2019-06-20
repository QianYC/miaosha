package nju.ycqian.stockservice.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MyMessageChanel {
    String OUTPUT = "toOrder";

    @Output(MyMessageChanel.OUTPUT)
    MessageChannel generateOrder();
}
