package com.cca.handler.fanout;

import com.cca.handler.AbstractConsumer;
import com.rabbitmq.client.Channel;
import message.Constants;
import message.GoToClassMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Shenzhen cca
 * @version 2018/8/31
 */
@Component
@RabbitListener(queues = Constants.FANOUT_QUEUE_TWO, containerFactory = Constants.FACTORY)
public class FanoutConsumerTwo extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(FanoutConsumerTwo.class);

  @RabbitHandler
  void goToClass(GoToClassMessage message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    run(message,channel,tag,()->{
      LOGGER.info("{} ，学生2回到教室", message.getMessage());
    });
  }

}
