package com.cca;

import com.cca.message.Message;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/21.
 */
@Component
public class MessageSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(Message message) {
        if (message.getDelayMinutes() != null) {
            rabbitTemplate.convertAndSend(message.exchange(), message.routingKey(), message, new ExpireMessageProcessor(message.getDelayMinutes()));
        } else {
            rabbitTemplate.convertAndSend(message.exchange(), message.routingKey(), message);
        }
    }


    private static class ExpireMessageProcessor implements MessagePostProcessor {

        private final Integer delayMinutes;

        public ExpireMessageProcessor(Integer delayMinutes) {
            this.delayMinutes = delayMinutes;
        }

        @Override
        public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message) throws AmqpException {
            message.getMessageProperties().setExpiration(String.valueOf(1000 * 60 * delayMinutes));
            return message;
        }
    }


}
