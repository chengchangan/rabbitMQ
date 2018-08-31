package com.cca.handler.fanout;

import com.cca.handler.AbstractConsumer;
import message.Constants;
import message.StudentMessage;
import message.UserMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Shenzhen cca
 * @version 2018/8/31
 */
@Component
@RabbitListener(queues = Constants.FANOUT_QUEUE_TWO,containerFactory = Constants.FACTORY )
public class FanoutConsumerTwo extends AbstractConsumer<UserMessage> {

  @Override
  public void process(UserMessage message) {

    System.out.println("fanout 2 消费了"+message.toString());
  }
}
