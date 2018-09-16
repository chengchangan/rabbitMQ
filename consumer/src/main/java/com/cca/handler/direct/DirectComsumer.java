package com.cca.handler.direct;

import com.cca.handler.AbstractConsumer;
import com.rabbitmq.client.Channel;
import message.CallNameMessage;
import message.Constants;
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
 * @version 2018/9/16
 */
@Component
@RabbitListener(queues = Constants.DIRECT_QUEUE, containerFactory = Constants.FACTORY)
public class DirectComsumer extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(DirectComsumer.class);

  @RabbitHandler
  void callName(CallNameMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    run(message, channel, tag, () -> {
      LOGGER.info("老师开始点名回答问题，学生：" + message.getStudentId() + "开始回答");
    });
  }
}
