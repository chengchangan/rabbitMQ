package com.cca.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/21.
 */
@Component
public class Handel {

    @RabbitListener(queues = "queue")
    public void process(String string){
        System.out.println(string);
    }
}
