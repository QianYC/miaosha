package nju.ycqian.orderservice.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Out {
    String OUTPUT = "toStock";

    @Output(Out.OUTPUT)
    MessageChannel output();
}
