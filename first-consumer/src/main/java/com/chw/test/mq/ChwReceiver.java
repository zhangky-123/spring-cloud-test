package com.chw.test.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class ChwReceiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Get msg ---- "+hello);
    }
}
