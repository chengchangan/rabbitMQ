package com.cca.handler.topic;

import com.cca.handler.AbstractConsumer;
import message.Constants;
import message.StudentMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/21.
 */
@Component
@RabbitListener(queues = Constants.TOPIC_QUEUE, containerFactory = Constants.FACTORY)
public class StudentConsumer extends AbstractConsumer<StudentMessage> {


  @Override
  public void process(StudentMessage message) {

    System.out.println(message.toString());
    if (message.getAge().equals(20)) {
      throw new RuntimeException("我想重试");
    } else {
      System.out.println("执行成功!");
    }
  }

}
