package com.cca.consumer.delay;

import com.cca.constants.Constants;
import com.cca.consumer.AbstractConsumer;
import com.cca.message.DelayMessage;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = Constants.DIRECT_DELAY_QUEUE, containerFactory = Constants.FACTORY)
public class DelayConsumer extends AbstractConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelayConsumer.class);

    @RabbitHandler
    void process(DelayMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        run(message, channel, tag, () -> {
            LOGGER.info("我是消费者，我收到的信息是：" + message.getMessage());
        });

    }

}
