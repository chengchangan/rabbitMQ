package com.cca.handler.topic;

import com.cca.handler.AbstractConsumer;
import com.rabbitmq.client.Channel;
import com.cca.message.ClearClassRoomOneMessage;
import com.cca.message.ClearClassRoomTwoMessage;
import com.cca.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2018/4/21.
 */
@Component
@RabbitListener(queues = Constants.TOPIC_QUEUE, containerFactory = Constants.FACTORY)
public class TopicConsumer extends AbstractConsumer{

  private static final Logger LOGGER = LoggerFactory.getLogger(TopicConsumer.class);

  @RabbitHandler
  void oneClear(ClearClassRoomOneMessage message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) Long tag)
      throws IOException {
    run(message,channel,tag,()->{
      LOGGER.info("{},学生1收到消息 ，开始打扫",message.getClearRoomMessage());
    });
  }

  @RabbitHandler
  void twoClear(ClearClassRoomTwoMessage message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) Long tag)
      throws IOException {
    run(message,channel,tag,()->{
      LOGGER.info("{},学生2收到消息 ，开始打扫",message.getClearRoomMessage());
    });
  }

}
