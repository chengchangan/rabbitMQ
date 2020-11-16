package com.cca.consumer.fanout;

import com.cca.constants.Constants;
import com.cca.consumer.AbstractConsumer;
import com.rabbitmq.client.Channel;
import com.cca.message.GoToClassMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 * 上课铃响了，同学们该上课了
 *
 * 场景：
 *  体现的是广播模式，所有监听该消息的人都进行消费
 *
 *  就像所有的学生听到铃响了，都得进教室，因为铃响不是为某一个学生大铃
 *
 * @author Shenzhen cca
 * @version 2018/8/31
 */
@Component
@RabbitListener(queues = Constants.FANOUT_QUEUE_ONE, containerFactory = Constants.FACTORY)
public class FanoutConsumerOne extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(FanoutConsumerOne.class);


  @RabbitHandler
  void goToClass(GoToClassMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    run(message, channel, tag, () -> {
      LOGGER.info("{} ，学生1回到教室", message.getMessage());
    });
  }
}
