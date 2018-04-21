package com.cca;

import message.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/21.
 */
@Component
public class MessageSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(Message message){
        rabbitTemplate.convertAndSend(message.exchange(),message.routingKey(),message);
    }
}
