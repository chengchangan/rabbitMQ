package com.cca.handler;

import message.StudentMessage;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/21.
 */
@Component
@RabbitListener(queues = "com.cca.test.create",containerFactory = "rabbitListenerContainerFactory")
public class StudentConsumer extends AbstractConsumer<StudentMessage>{


    @Override
    public void process(StudentMessage message) {

        System.out.println(message.toString());
        if (message.getAge().equals(20)){
            throw new RuntimeException("我想重试");
        }else {
            System.out.println("执行成功!");
        }
    }

}
