package com.cca.handler;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

public abstract class AbstractConsumer<T extends Message> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConsumer.class);

  public abstract void process(T message);

  @RabbitHandler
  public void run(T message,Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    try {
      process(message);
      channel.basicAck(tag,true);
    }catch (RuntimeException e){
      LOGGER.error("消息处理异常！{},{},{}", this.getClass().getName(), message, e.getMessage());
      channel.basicReject(tag,true);
    }catch (Exception e){
      LOGGER.error("消息处理异常！{},{}", this.getClass().getName(), JSON.toJSONString(message));
      LOGGER.error("消息处理异常！", e);
      channel.basicReject(tag,false);
    }
  }
}
