package com.cca.handler;

import message.StudentMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/21.
 */
@Component
public class StudentHandel {

    @RabbitListener(queues = "com.cca.test.create")
    public void process(StudentMessage object){
        try {
//            System.out.println("=========111:"+object.toString());
//            if (true){
//                throw new Exception();
//            }
            System.out.println("=========222:"+object.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
